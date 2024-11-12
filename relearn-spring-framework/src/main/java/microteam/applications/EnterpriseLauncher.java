package microteam.applications;

import microteam.services.BusinessCalculationService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan("microteam.services")
public class EnterpriseLauncher {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext
                (EnterpriseLauncher.class);
        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println);

        System.out.println(
                context.getBean(BusinessCalculationService.class).findMax());
    }
}

