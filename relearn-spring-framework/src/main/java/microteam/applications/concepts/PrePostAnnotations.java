package microteam.applications.concepts;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component //Step 31 Perform Dependency Injection
class CC {
    private ThirdDependency thirdDependency;

    public CC(ThirdDependency thirdDependency) {
        super();
        this.thirdDependency = thirdDependency;
        System.out.println("All Dependencies are Ready");
    }
    @PostConstruct //Step 32 Post DI Initialization
    public void initialize() {
        thirdDependency.getReady();
    }

    @PreDestroy //Step 33 //Debug for Missing sout Output
    public void cleanup(){
        System.out.println("All Dependencies are Cleaned");
    }
}

@Component //Step 31
class ThirdDependency {
    public void getReady(){
        System.out.println("Third Dependency Logic Executed");
    }
}

@Configuration
@ComponentScan("microteam.applications.concepts")
public class PrePostAnnotations {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext
                (PrePostAnnotations.class);
        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println);
    }
}

