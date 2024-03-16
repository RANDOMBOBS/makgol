package com.org.makgol.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class logAspect {

    @Around("com.org.makgol.annotation.LogExecutionTime")
    public Object logExcutionTimeAnno(ProceedingJoinPoint joinPoint) throws Throwable{



        try {
            long startTime = System.currentTimeMillis();

            Object proceed = joinPoint.proceed();

            long stopTime = System.currentTimeMillis();
            System.out.println(stopTime - startTime);
            return proceed;

        } finally {
            log.info("execute error{}", 1);
        }




    }

    @Around("execution(* com.org.makgol.controller.*.*(..))")
    public Object logExcutionTimeVent(ProceedingJoinPoint joinPoint) throws Throwable{



        try {
            long startTime = System.currentTimeMillis();

            Object proceed = joinPoint.proceed();

            long stopTime = System.currentTimeMillis();
            System.out.println(stopTime - startTime);
            return proceed;

        } finally {
            log.info("execute error{}", 1);
        }


    }
}
