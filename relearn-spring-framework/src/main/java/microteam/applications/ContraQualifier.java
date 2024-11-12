package microteam.applications;

import microteam.game.GameRunnerSQ;
import microteam.game.GamingConsole;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("microteam.game") //Step 12 Guide Spring to Search for PacMan Component
public class ContraQualifier {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ContraQualifier.class);
        context.getBean(GamingConsole.class).up();
        //Step 17.2 Run Me Contra
        context.getBean(GameRunnerSQ.class).run();
    }
}

