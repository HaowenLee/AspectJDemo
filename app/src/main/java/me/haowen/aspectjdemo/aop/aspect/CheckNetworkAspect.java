package me.haowen.aspectjdemo.aop.aspect;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import me.haowen.aspectjdemo.aop.annotation.CheckNetwork;

@Aspect
public class CheckNetworkAspect {

    @Pointcut("execution(@me.haowen.aspectjdemo.aop.annotation.CheckNetwork * *(..))")
    public void checkNetwork() {
    }

    @Around("checkNetwork()")
    public void checkNetworkAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("CheckNetwork 注解只能用于方法上");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        CheckNetwork checkNetwork = methodSignature.getMethod().getAnnotation(CheckNetwork.class);
        if (checkNetwork == null) {
            return;
        }
        if (NetworkUtils.isConnected()) {
            joinPoint.proceed();
        } else {
            ToastUtils.showShort("网络不可用");
        }
    }
}