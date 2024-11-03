package microteam.springaspectop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//import java.util.logging.Logger;

//AOP Configuration
@Aspect // (Pointcut + Advice)
@Configuration
public class AfterAOPAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //kind of methods to be intercepted - Weaver Framework
    @AfterReturning(
            //value = "execution(* microteam.springaspectop.venture.*.*(..))",
            value = "microteam.springaspectop.aspect.CommonJointPointConfig.businessLayerExecution()",
            returning = "result")
    //Pointcut
    public void afterReturning(JoinPoint joinPoint, Object result) {
        //Advice
        logger.info("{} returned with value - {}", joinPoint, result); //Specific Execution Interception
    }

    @AfterThrowing(
            value = "execution(* microteam.springaspectop.venture.*.*(..))",
            throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Object exception) {
        logger.info("{} returned with value - {}", joinPoint, exception);
    }

    @After(value = "execution(* microteam.springaspectop.venture.*.*(..))")
    public void after(JoinPoint joinPoint) {
        logger.info("after execution of {}", joinPoint);
    }
}
