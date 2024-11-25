package microteam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Gateway {

    @GetMapping("/gateway/status")
    public String gatewayStatus() {
        return "Gateway is up and running!";
    }
}
