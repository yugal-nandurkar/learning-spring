package microteam.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.EnableRetry;

import java.util.List;
import java.util.Optional;

@Component
@EnableRetry  // Enables retry functionality at class level
public class ServiceDiscoveryRunner implements CommandLineRunner {

    @Autowired
    private ConsulService consulService;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Runs the service discovery actions when the application starts.
     * This method is executed by Spring Boot on startup.
     *
     * @param args Command line arguments (not used here)
     * @throws Exception If an error occurs during service discovery
     */
    @Override
    public void run(String... args) throws Exception {
        // Fetch and print all registered services
        List<String> serviceNames = consulService.getAllServiceNames();
        System.out.println("Registered services: " + serviceNames);

        // Get and print instances of a specific service (example-service)
        handleServiceInstances("example-service");

        // Get and print the first available instance of the service (example-service)
        handleFirstInstance("example-service");

        // Try fetching service metadata from a URL, with retry on failure
        String serviceMetadataUrl = "http://localhost:8080/metadata";
        fetchServiceMetadataWithRetry(serviceMetadataUrl);
    }

    /**
     * Handles service instances by printing their details.
     * It also manages service lookup failures.
     *
     * @param serviceName The name of the service to discover
     */
    private void handleServiceInstances(String serviceName) {
        try {
            consulService.printServiceInstances(serviceName);
        } catch (Exception e) {
            System.out.println("Error fetching instances for service: " + serviceName);
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Handles fetching the first instance of a service.
     * It manages potential service unavailability and logs the error.
     *
     * @param serviceName The name of the service to discover
     */
    private void handleFirstInstance(String serviceName) {
        Optional<ServiceInstance> firstInstance = consulService.getFirstInstance(serviceName);
        if (firstInstance.isPresent()) {
            System.out.println("First Instance Details:");
            ServiceInstance instance = firstInstance.get();
            System.out.println(" - URI: " + instance.getUri());
            System.out.println(" - Metadata: " + instance.getMetadata());
        } else {
            System.out.println("No available instances for service: " + serviceName);
        }
    }

    /**
     * Fetches service metadata from a given URL, with retry logic for failure scenarios.
     *
     * @param url The URL to fetch service metadata from
     */
    @Retryable(maxAttempts = 3, value = HttpStatusCodeException.class)  // Retry on HttpStatusCodeException
    private void fetchServiceMetadataWithRetry(String url) {
        try {
            String metadata = restTemplate.getForObject(url, String.class);
            System.out.println("Fetched Service Metadata: " + metadata);
        } catch (HttpStatusCodeException e) {
            System.out.println("Failed to fetch metadata from " + url + ". Retrying...");
            throw e;  // Rethrow to trigger retry
        } catch (Exception e) {
            System.out.println("Unexpected error occurred while fetching metadata from " + url);
            e.printStackTrace();
        }
    }
}
