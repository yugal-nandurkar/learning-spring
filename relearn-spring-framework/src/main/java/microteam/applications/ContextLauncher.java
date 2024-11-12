package microteam.applications;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan("microteam.game") //Step 12 Guide Spring to Search for PacMan Component
public class ContextLauncher {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext
                (ContextLauncher.class);
        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println);
    }
}

