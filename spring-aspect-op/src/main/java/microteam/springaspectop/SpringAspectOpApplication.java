package microteam.springaspectop;

import microteam.springaspectop.venture.Business;
import microteam.springaspectop.venture.Enterprise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

//import java.util.logging.Logger;

@SpringBootApplication
public class SpringAspectOpApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Business business;

    @Autowired
    private Enterprise enterprise;

    public static void main(String[] args) {
        SpringApplication.run(SpringAspectOpApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info(business.calculateSomething());
        logger.info(enterprise.calculateSomething());
    }
}
