package microteam.springaspectop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//import java.util.logging.Logger;

//AOP Configuration
@Aspect // (Pointcut + Advice)
@Configuration
public class MethodExecutionCalculationAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //kind of methods to be intercepted - Weaver Framework
    //@Around("execution(* microteam.springaspectop.venture.*.*(..))")
    //@Around("microteam.springaspectop.aspect.CommonJointPointConfig.businessLayerExecution()")
    @Around("microteam.springaspectop.aspect.CommonJointPointConfig.trackTimeAnnotation()")
    //Pointcut
    public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        //startTime = x
        long startTime = System.currentTimeMillis();
        //Allow Execution of Method
        joinPoint.proceed();
        //endTime = y
        long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("Time taken by {} is - {}", joinPoint, timeTaken); //Specific Execution Interception
    }
}
