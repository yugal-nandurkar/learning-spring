package microteam.applications.concepts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//Step 18 - Your Business Class with Dependency 1 & 2
@Component
class YourBusiness{
    @Autowired //Step 20 Field Injections
    Dependency1 dependency1;
    @Autowired
    Dependency2 dependency2;

    @Autowired //Step 21 Setter Injections
    public void setDependency1(Dependency1 dependency1) {
        System.out.println("Setting dependency1 to " + dependency1);
        this.dependency1 = dependency1;
    }

    @Autowired
    public void setDependency2(Dependency2 dependency2) {
        System.out.println("Setting dependency2 to " + dependency2);
        this.dependency2 = dependency2;
    }

    @Autowired // Step 22 Constructor Injection (Autowired not Mandatory)
    public YourBusiness(Dependency1 dependency1, Dependency2 dependency2) {
        System.out.println("Constructing YourBusiness");
        this.dependency1 = dependency1;
        this.dependency2 = dependency2;
    }

    @Override
    public String toString() {
        return "YourBusiness{" +
                "dependency1=" + dependency1 +
                ", dependency2=" + dependency2 +
                '}';
    }
}

@Component
class Dependency1{}

@Component
class Dependency2{}

@Configuration
@ComponentScan("microteam.applications") //Step 19 Go Explicit on Default
public class DependencyInjector {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext
                (DependencyInjector.class);
        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println);
        System.out.println(context.getBean(YourBusiness.class));
    }
}

