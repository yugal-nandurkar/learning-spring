package microteam.game;

import org.springframework.stereotype.Component;

@Component //Step 11 Asking Spring to Create Beans
public class PacManGame implements GamingConsole{
    public void up() {
        System.out.println("Up");
    }
    public void down() {
        System.out.println("Down");
    }
    public void left() {
        System.out.println("Left");
    }
    public void right() {
        System.out.println("Right");
    }

    @Override
    public void shoot() {
        // Fixture for GamingConsole Implementation
    }
}
