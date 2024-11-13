package microteam.applications.concepts;

import microteam.configuration.Address;
import microteam.configuration.SpringConfigurator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

//@SpringBootApplication
public class SpringMagic {
    public static void main(String[] args) {
       //Step 3 - Launch a Spring Context (IOC Container(other:BeanFactory) to Manage Beans)
        var context = new AnnotationConfigApplicationContext(SpringConfigurator.class);
        //Step 4 - Configure Spring Managed Entities(Beans) - @Configuration
        System.out.println(context.getBean("name"));
        System.out.println(context.getBean("age"));
        System.out.println(context.getBean("first_person"));
        System.out.println(context.getBean("american_address"));
        System.out.println(context.getBean("second_person"));
        System.out.println(context.getBean(Address.class));
        System.out.println(context.getBean("personMethodCall"));
        System.out.println(context.getBean("personParameters"));
        //Step 5 - To get list of Spring Beans
        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println);
    }
}
