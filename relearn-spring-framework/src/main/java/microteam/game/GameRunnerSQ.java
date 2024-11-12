package microteam.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component //Step14
public class GameRunnerSQ {
    GamingConsole game;
    //Step 17 Add Qualifier For SuperContraGame to reign over Primary MarioGame
    public GameRunnerSQ(@Qualifier("SuperQualifier")GamingConsole game) {
        this.game = game;
    }

    public void run() {
        System.out.println("Running game: " + game);
        game.up();
        game.down();
        game.left();
        game.right();
        game.shoot();
    }
}
