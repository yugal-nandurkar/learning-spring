package microteam.springaspectop.aspect;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

public class CommonJointPointConfig {
    @Pointcut("execution(* microteam.springaspectop.data.*.*(..))")
    public void dataLayerExecution() {}

    @Pointcut("execution(* microteam.springaspectop.venture.*.*(..))")
    public void businessLayerExecution() {}

    @Pointcut("microteam.springaspectop.aspect.CommonJointPointConfig.businessLayerExecution() && microteam.springaspectop.aspect.CommonJointPointConfig.dataLayerExecution()")
    public void allLayerExecution() {}

    @Pointcut("bean(*dao*)")
    public void containingDao() {}

    @Pointcut("within(microteam.springaspectop.data..*)")
    public void dataLayerExecutionWithin() {}

    @Pointcut("@annotation(microteam.springaspectop.aspect.TrackTime)")
    public void trackTimeAnnotation(){}
}
