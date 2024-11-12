package microteam.applications;

import microteam.configuration.GamingConfigurator;
import microteam.game.GameRunnerSB;
import microteam.game.GamingConsole;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppGamingSpringBeans {
    public static void main(String[] args) {
        //Step 8
        var context = new AnnotationConfigApplicationContext(GamingConfigurator.class);
        context.getBean(GamingConsole.class).up();
        context.getBean(GameRunnerSB.class).run();
    }
}
