package com.sngular.qa.ecommerce.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ControllerAspect {

    static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut(value = "execution(* com.sngular.qa.ecommerce.controller.*.*(..))")
    public void executeLogging() {
        // this method is empty
    }

    @Around("executeLogging()")
    public Object loggingAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        String className = "CLASS: [" + joinPoint.getTarget().getClass().getSimpleName() + "],";
        String methodName = " METHOD: [" + joinPoint.getSignature().getName() + "()],";
        logger.info("{} {} REQUEST: ", className, methodName);
        if (joinPoint.getArgs().length > 0) {
            Arrays.stream(joinPoint.getArgs()).forEach(m -> logger.info(m.toString()));
        } else {
            logger.info("[]");
        }
        logger.info("{} {} RESPONSE: {}", className, methodName, proceed);
        return proceed;
    }
}
