package microteam.springaspectop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//import java.util.logging.Logger;

//AOP Configuration
@Aspect // (Pointcut + Advice)
@Configuration
public class UserAccessAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //kind of methods to be intercepted - Weaver Framework
    //@Before("execution(* microteam.springaspectop.venture.*.*(..))")
    //@Before("execution(* microteam.springaspectop..*.*(..))")
    @Before("microteam.springaspectop.aspect.CommonJointPointConfig.dataLayerExecution()")
    //Pointcut
    public void before(JoinPoint joinPoint) {
        //Advice
        logger.info("Check for User Access");
        logger.info("Allowed Execution for - {}", joinPoint); //Specific Execution Interception
    }

}
