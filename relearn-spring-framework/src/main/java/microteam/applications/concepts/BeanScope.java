package microteam.applications.concepts;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

@Component//Step 29 Singleton Bean
// (Java(Reference: Gang of Four Book) vs Spring)
    // 1 JVM Object Instance vs 1 Spring IOC Container Instance
class Normal {

}

@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) //Step 29
@Component
class Prototype {

}

@Configuration
@ComponentScan("microteam.applications")
public class BeanScope {
    public static void main(String[] args) {
        /* Web Aware Spring Application Contexts:
        Request, Session, Application, Websocket */
        var context = new AnnotationConfigApplicationContext
                (BeanScope.class);
        //Step 30 Observed Output - Similar Object Signature
        System.out.println(context.getBean(Normal.class));
        System.out.println(context.getBean(Normal.class));
        //Step 30 Observed Output - Different Object Signatures
        System.out.println(context.getBean(Prototype.class));
        System.out.println(context.getBean(Prototype.class));
        System.out.println(context.getBean(Prototype.class));
      /*  Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println);
   */ }
}

