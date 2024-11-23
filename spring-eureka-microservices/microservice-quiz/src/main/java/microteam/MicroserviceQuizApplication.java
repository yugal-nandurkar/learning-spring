package microteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "microteam.feign")
public class MicroserviceQuizApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceQuizApplication.class, args);
    }

}
