package microteam.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheck.class);

    /**
     * Health-check endpoint for Consul or other monitoring tools.
     *
     * @return A simple "UP" status
     */
    @GetMapping("/health")
    public String healthCheck() {
        logger.info("Health check endpoint accessed.");
        return "UP";
    }
}
