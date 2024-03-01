package com.systemsltd.employeemanagement.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.systemsltd.employeemanagement.controller.*.*(..))")
    private void restControllerMethods() {

    }

    @Before("restControllerMethods()")
    public void logMethodEntry(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        logger.info("Entering: " + methodSignature.toShortString());
        RequestMapping requestMapping = methodSignature.getMethod().getAnnotation(RequestMapping.class);
        if (requestMapping != null) {
            RequestMethod[] requestMethods = requestMapping.method();
            String method = Arrays.stream(requestMethods)
                    .map(Enum::name)
                    .collect(Collectors.joining(", "));
            logger.info("Request Method(s): " + method);
        }

        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            logger.info("Request Parameters: " + Arrays.toString(args));
        }
    }

    @AfterReturning(pointcut = "restControllerMethods()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        logger.info("Exiting: " + joinPoint.getSignature().toShortString());
        logger.info("Response: " + result);
    }
}
