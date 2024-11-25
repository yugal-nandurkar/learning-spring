package microteam.controller;

import microteam.services.Health;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheck {

    @Autowired
    private Health healthService;

    /**
     * Endpoint to check the health of the service.
     * @return String with health status.
     */
    @GetMapping("/status")
    public String getHealthStatus() {
        return healthService.getHealthStatus();
    }

    /**
     * Endpoint to perform detailed health check.
     * @return String with detailed health information.
     */
    @GetMapping("/detailed")
    public String getDetailedHealthStatus() {
        return healthService.getDetailedHealthStatus();
    }
}
