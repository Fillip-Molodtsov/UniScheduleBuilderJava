package com.springproj.schedulebuilder.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Aspect
public class LoggingAspects {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.springproj.schedulebuilder.util.LogExecTime)")
    public void logExecTimePointCut(){}


    @Around("logExecTimePointCut()")
    public Object logExecutionTimeAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logger.info("[logExecutionTimeAspect] " + joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }

    @Around("@annotation(com.springproj.schedulebuilder.util.LogSignature)")
    public Object logSignatureAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("[logSignatureAspect] Entered: {}.{}() with arguments = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()) );
        Object proceed = joinPoint.proceed();
        logger.info("[logSignatureAspect] Exited: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), proceed);
        return proceed;
    }
}
