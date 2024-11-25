package microteam.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceMetadataBuilder {

    // Injecting values from application.properties.log or application.yml
    @Value("${service.version:1.0.0}")
    private String version;

    @Value("${service.environment:production}")
    private String environment;

    @Value("${service.team:microservices-team}")
    private String team;

    @Value("${service.region:us-east-1}")
    private String region;

    /**
     * Builds custom metadata for the service instance.
     *
     * @return A map of metadata
     */
    public Map<String, String> buildMetadata() {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("version", version);
        metadata.put("environment", environment);
        metadata.put("team", team);
        metadata.put("region", region);
        return metadata;
    }
}
