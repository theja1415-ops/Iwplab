package com.example.aopdemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.example.aopdemo.service.*.*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        
        System.out.println("Executing method: " + methodName);
        System.out.println("Arguments: ");
        for (Object arg : args) {
            System.out.println(" - " + arg);
        }

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        System.out.println("Method " + methodName + " executed successfully");
        System.out.println("Return Value: " + result);
        System.out.println("⏱ Execution Time: " + (endTime - startTime) + " ms");
        
        return result;
    }
}