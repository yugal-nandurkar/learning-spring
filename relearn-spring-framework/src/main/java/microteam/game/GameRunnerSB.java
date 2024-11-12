package microteam.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component //Step14
public class GameRunnerSB {
    GamingConsole game;
    public GameRunnerSB(GamingConsole game) {
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
