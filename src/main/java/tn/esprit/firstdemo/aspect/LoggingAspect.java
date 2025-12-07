package tn.esprit.firstdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // Pointcut pour la couche service et ses sous-packages
    @Pointcut("execution(* tn.esprit.firstdemo.service..*(..))")
    public void serviceLayer() {}

    @Before("serviceLayer()")
    public void logMethodEntry(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String method = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("[AOP][BEFORE] {}.{}() called with args={}", className, method, Arrays.toString(args));
    }

    @AfterReturning(pointcut = "serviceLayer()", returning = "result")
    public void logAfterReturning(JoinPoint jp, Object result) {
        String cls = jp.getSignature().getDeclaringType().getSimpleName();
        String m = jp.getSignature().getName();
        if (result == null) {
            log.info("[AOP][AFTER RETURNING] {}.{}() returned void or null", cls, m);
        } else {
            log.info("[AOP][AFTER RETURNING] {}.{}() returned={}", cls, m, result);
        }
    }

    @AfterThrowing(pointcut = "serviceLayer()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String method = joinPoint.getSignature().getName();
        log.error("[AOP][AFTER THROWING] {}.{}() threw={}", className, method, ex.toString(), ex);
    }

    @After("serviceLayer()")
    public void logAfter(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String method = joinPoint.getSignature().getName();
        log.debug("[AOP][AFTER] {}.{}() finished", className, method);
    }

    // NOTE: Around removed to avoid duplicate timing logs (PerformanceAspect handles timing)
}