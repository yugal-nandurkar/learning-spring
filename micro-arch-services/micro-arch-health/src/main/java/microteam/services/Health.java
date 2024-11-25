package microteam.services;

import org.springframework.stereotype.Service;

@Service
public class Health{

    /**
     * Check if the service is healthy.
     * @return Health status string.
     */
    public String getHealthStatus() {
        // Here you can add your logic to check the health of the service, database, etc.
        return "Service is running smoothly!";
    }

    /**
     * Provides detailed health information for monitoring tools like Prometheus or Actuator.
     * @return Detailed health information.
     */
    public String getDetailedHealthStatus() {
        // In a real application, you might query databases, external APIs, etc.
        return "{ \"status\": \"UP\", \"db\": \"connected\", \"dependencies\": \"OK\" }";
    }
}
