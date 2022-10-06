package com.example.note.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.example.note.controller..*(..)) || execution(* com.example.note.service..*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        LOGGER.info("Starting: {}.{}()", getClassName(joinPoint), getMethodName(joinPoint));
    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        LOGGER.info("Finished: {}.{}()", getClassName(joinPoint), getMethodName(joinPoint));
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        // 執行方法並取得回應
        Object response = joinPoint.proceed();

        LOGGER.info("Finished in {}ms", System.currentTimeMillis() - start);

        return response;
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
        LOGGER.info("Throw exception: {}.{}", getClassName(joinPoint), getMethodName(joinPoint));
        LOGGER.info("message: {}", throwable.getMessage());
    }

    private String getClassName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getName();
    }

    private String getMethodName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }

}
