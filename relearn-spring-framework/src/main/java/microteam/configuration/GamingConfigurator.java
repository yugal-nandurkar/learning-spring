package microteam.configuration;

import microteam.game.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GamingConfigurator {
    //Step 7
    @Bean
    public GamingConsole game() {
        var game = new PacManGame();
        return game;
    }

    //Step 9
    @Bean
    public GameRunnerSB gameRunnerSB(GamingConsole game) {
        var gameRunnerSB = new GameRunnerSB(game);
        return gameRunnerSB;
    }
}
