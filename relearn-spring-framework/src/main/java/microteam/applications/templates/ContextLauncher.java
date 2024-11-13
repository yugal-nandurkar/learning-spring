package microteam.applications.templates;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan("microteam.applications") //Step 12.A Template Application
public class ContextLauncher {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext
                (ContextLauncher.class);
        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println);
    }
}

