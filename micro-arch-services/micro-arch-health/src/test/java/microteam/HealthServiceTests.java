package microteam;

import microteam.services.Health;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HealthServiceTests {

    private final Health healthService = new Health();

    @Test
    void testHealthStatus() {
        String status = healthService.getHealthStatus();
        assertNotNull(status);
        assertEquals("Service is running smoothly!", status);
    }

    @Test
    void testDetailedHealthStatus() {
        String detailedStatus = healthService.getDetailedHealthStatus();
        assertNotNull(detailedStatus);
        assertTrue(detailedStatus.contains("\"status\": \"UP\""));
    }
}

