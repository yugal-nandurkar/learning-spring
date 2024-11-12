package microteam.game;

public class GameRunnerLC {
    GamingConsole game;
    public GameRunnerLC(GamingConsole game) {
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
