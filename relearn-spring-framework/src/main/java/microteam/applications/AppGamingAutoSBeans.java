package microteam.applications;

import microteam.game.GameRunnerSB;
import microteam.game.GamingConsole;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("microteam.game") //Step 12 Guide Spring to Search for PacMan Component
public class AppGamingAutoSBeans {
    //Step 10
  /*  @Bean
    public GamingConsole game() {
        var game = new PacManGame();
        return game;
    }*/

    //Step 13
    /*@Bean
    public GameRunnerSB gameRunnerSB(GamingConsole game) {
        System.out.println("Parameter:" + game);
        var gameRunnerSB = new GameRunnerSB(game);
        return gameRunnerSB;
    }*/

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppGamingAutoSBeans.class);
        context.getBean(GamingConsole.class).up();
        context.getBean(GameRunnerSB.class).run();
    }
}

