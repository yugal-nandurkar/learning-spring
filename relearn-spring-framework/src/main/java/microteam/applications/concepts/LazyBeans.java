package microteam.applications.concepts;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

//Step 26
@Component
class CA {

}

@Component
@Lazy // Step 27 Avoided Bean Initialization at Startup
class CB {
    private CA ca;

    //CB using CA as bean for initialization
    public CB(CA ca) {
        //Logic
        System.out.println("Some Initialization Logic");
        this.ca = ca;
    }

    //Step 28.1
    public void first_task(){
        System.out.println("First Task");
    }
}

@Configuration
@ComponentScan("microteam.applications")
public class LazyBeans {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext
                (LazyBeans.class);
        System.out.println("Context Initialization Completed");
        //Step 28.2
        context.getBean(CB.class).first_task();
        /*
        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println);
    */}
}

