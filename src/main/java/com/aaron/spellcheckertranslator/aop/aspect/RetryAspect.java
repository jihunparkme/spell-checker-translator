package com.aaron.spellcheckertranslator.aop.aspect;

import com.aaron.spellcheckertranslator.aop.annotation.Retry;
import com.aaron.spellcheckertranslator.commin.exception.ExternalApiException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RetryAspect {

    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        int maxTry = retry.value();
        Exception exceptionHolder = null;
        for (int retryCount = 1; retryCount <= maxTry; retryCount++) {
            try {
                log.info("[Retry] try count={}/{}, {}", retryCount, maxTry, joinPoint.getSignature());
                return joinPoint.proceed();
            } catch (ExternalApiException e) {
                exceptionHolder = e;
            }
        }
        throw exceptionHolder;
    }
}
