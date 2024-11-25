package microteam.discovery;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // Simulate a health check, e.g., check database or external service status
        boolean serviceHealthy = true; // Replace with actual health check logic

        if (serviceHealthy) {
            return Health.up().withDetail("service", "Service is running smoothly").build();
        } else {
            return Health.down().withDetail("error", "Service is facing issues").build();
        }
    }
}
