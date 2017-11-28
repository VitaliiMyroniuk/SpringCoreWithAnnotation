package com.epam.myroniuk.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vitalii Myroniuk
 */
@Component
@Aspect
public class StatisticsAspect {
    private static final Logger LOGGER = Logger.getLogger(StatisticsAspect.class);
    private Map<Class<?>, Integer> count = new HashMap<>();

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {
    }

    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getTarget().getClass();
        count.put(clazz, count.getOrDefault(clazz, 0) + 1);
        LOGGER.info(count);
    }
}
