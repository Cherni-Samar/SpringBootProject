package tn.esprit.firstdemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class PerformanceAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final long SLOW_THRESHOLD_MS = 1000L;

    @Around("execution(* tn.esprit.firstdemo.service..*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result;
        try {
            result = pjp.proceed();
            return result;
        } finally {
            long elapsed = System.currentTimeMillis() - start;
            String signature = pjp.getSignature().toShortString();
            if (elapsed > SLOW_THRESHOLD_MS) {
                log.warn("[AOP][PERF] {} executed in {} ms (SLOW)", signature, elapsed);
            } else {
                // changed to INFO so visible by default
                log.info("[AOP][PERF] {} executed in {} ms", signature, elapsed);
            }
        }
    }
}