package me.haowen.aspectjdemo.aop.aspect;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;

import io.reactivex.functions.Consumer;
import me.haowen.aspectjdemo.aop.annotation.CheckPermission;

/**
 * 权限检查
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
@Aspect
public class CheckPermissionAspect {

    @Pointcut("execution(@me.haowen.aspectjdemo.aop.annotation.CheckPermission * *(..))")
    public void CheckPermission() {
    }

    @SuppressLint("CheckResult")
    @Around("CheckPermission()")
    public void CheckPermissionAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("CheckPermission 注解只能用于方法上");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        CheckPermission checkPermission = methodSignature.getMethod().getAnnotation(CheckPermission.class);
        if (checkPermission == null) {
            return;
        }
        String[] permissions = checkPermission.permissions();
        System.out.println(Arrays.toString(permissions));
        if (permissions.length == 0) {
            return;
        }
        Object object = joinPoint.getThis();
        RxPermissions rxPermissions;
        if (object instanceof FragmentActivity) {
            rxPermissions = new RxPermissions((FragmentActivity) object);
        } else if (object instanceof Fragment) {
            rxPermissions = new RxPermissions((Fragment) object);
        } else {
            return;
        }
        rxPermissions.request(permissions).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    try {
                        joinPoint.proceed();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                } else {
                    ToastUtils.showShort("Permission denied.");
                }
            }
        });
    }
}