package microteam.game;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary //Step 15
public class MarioGame implements GamingConsole{
    public void up() {
        System.out.println("Jump");
    }
    public void down() {
        System.out.println("Dodge");
    }
    public void left() {
        System.out.println("Go Back");
    }
    public void right() {
        System.out.println("Accelerate");
    }

    @Override
    public void shoot() {
        // Fixture for GamingConsole Implementation
    }
}
