package com.ojas.securesafe.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.ojas.securesafe.service..*(..)) || execution(* com.ojas.securesafe.controller..*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String correlationId = generateCorrelationId();

        try {
            // Set correlation ID in the MDC for logging
            MDC.put("correlationId", correlationId);

            // Log entering before method execution
            log.info("Entering: {}.{}", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName());

            // Proceed with the actual method execution
            Object result = joinPoint.proceed();

            // Log exiting after method execution
            log.info("Exiting: {}.{}. Returned value: {}", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName(), result);

            return result;
        } finally {
            // Clear the correlation ID from the MDC after method execution
            MDC.remove("correlationId");
        }
    }

    private String generateCorrelationId() {
        // Generate a unique correlation ID
        return UUID.randomUUID().toString();
    }

    @AfterThrowing(pointcut = "execution(* com.ojas.securesafe..*(..))", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL", e);
    }
}
