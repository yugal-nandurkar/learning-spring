package microteam.game;

public class GameRunnerTC {
    MarioGame game;
    public GameRunnerTC(MarioGame game) {
        // GameRunnerTC is tightly coupled to MarioGame
        this.game = game;
    }

    public void run() {
        System.out.println("Running game: " + game);
        game.up();
        game.down();
        game.left();
        game.right();
       //game.shoot();
    }
}
