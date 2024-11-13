package microteam.applications.concepts;

import microteam.game.GameRunnerSB;
import microteam.game.GameRunnerSQ;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Arrays;

// @Configuration //This is New Java Configuration
//@ComponentScan("microteam.applications.concepts")
public class ArchaicXMLConfiguration {
    public static void main(String[] args) {
        //Step 35
        var context = new ClassPathXmlApplicationContext("context_configuration.xml");
        System.out.println(context.getBean("xml_bean"));
        System.out.println(context.getBean("int_bean"));
        System.out.println(context.getBean("game"));
        System.out.println(context.getBean("game_runner"));
        context.getBean(GameRunnerSB .class).run();

        /* //This is New Java Configuration
        var context = new AnnotationConfigApplicationContext
                (ArchaicXMLConfiguration.class);
        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println); */
    }
}

