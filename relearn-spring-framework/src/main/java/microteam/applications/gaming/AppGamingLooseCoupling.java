package microteam.applications.gaming;

import microteam.game.GameRunnerLC;
import microteam.game.SuperContraGame;

public class AppGamingLooseCoupling {
    public static void main(String[] args) {
        //Step 2 - for loose coupling with GamingConsole Interface
        //Object Creation
        // Switch - var game = new MarioGame();
        // Switch - var game = new PacManGame();
        var game = new SuperContraGame();
        //Object Creation + Dependency(Game) Wiring
        var gameRunnerLC = new GameRunnerLC(game); //game injection
        gameRunnerLC.run();
    }
}
