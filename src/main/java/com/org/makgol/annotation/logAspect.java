package com.org.makgol.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class logAspect {

    @Around("@annotation(LogExcutionTime)")
    public Object logExcutionTime(ProceedingJoinPoint joinPoint) throws Throwable{

        long startTime = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime);

        return proceed;
    }
}
