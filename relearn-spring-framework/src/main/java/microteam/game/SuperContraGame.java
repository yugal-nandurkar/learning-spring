package microteam.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("SuperQualifier")//Step 16
public class SuperContraGame implements GamingConsole{
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
    public void shoot() {
        System.out.println("Shoot");
    }
}
