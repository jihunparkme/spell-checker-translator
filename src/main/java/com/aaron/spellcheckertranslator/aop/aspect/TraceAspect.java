package com.aaron.spellcheckertranslator.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TraceAspect {

    @Before("@annotation(com.aaron.spellcheckertranslator.aop.annotation.Trace)")
    public void traceBefore(JoinPoint joinPoint) {
        log.info("[Before] {}", joinPoint.getClass());
    }

    @After("@annotation(com.aaron.spellcheckertranslator.aop.annotation.Trace)")
    public void traceAfter(JoinPoint joinPoint) {
        log.info("[After] {}", joinPoint.getSignature());
    }
}
