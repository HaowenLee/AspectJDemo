package me.haowen.aspectjdemo.aop.aspect;

import android.annotation.SuppressLint;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import me.haowen.aspectjdemo.aop.annotation.CheckNetwork;

/**
 * 网络检查
 */
@Aspect
public class DurationCalculateAspect {

    @Pointcut("execution(@me.haowen.aspectjdemo.aop.annotation.DurationCalculate * *(..))")
    public void durationCalculate() {
    }

    @SuppressLint("DefaultLocale")
    @Around("durationCalculate()")
    public void durationCalculateAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        LogUtils.d(String.format("耗时：%dms", System.currentTimeMillis() - startTime));
    }
}