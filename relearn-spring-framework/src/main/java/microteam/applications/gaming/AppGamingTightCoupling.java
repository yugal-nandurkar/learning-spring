package microteam.applications.gaming;

import microteam.game.GameRunnerTC;
import microteam.game.MarioGame;

public class AppGamingTightCoupling {
    public static void main(String[] args) {
        var marioGame = new MarioGame();
        var gameRunnerTC = new GameRunnerTC(marioGame);
        gameRunnerTC.run();
    }
}
