package microteam.discovery;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsulService {

    private final DiscoveryClient discoveryClient;

    public ConsulService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    /**
     * PostConstruct to initialize resources if needed.
     */
    @PostConstruct
    public void init() {
        // Initialization logic if necessary
        System.out.println("ConsulService initialized.");
    }

    /**
     * PreDestroy to clean up resources if needed.
     */
    @PreDestroy
    public void cleanup() {
        // Cleanup logic if necessary
        System.out.println("ConsulService cleaned up.");
    }

    /**
     * Retrieves a list of service instances registered under the specified service name.
     *
     * @param serviceName the name of the service to look up
     * @return a list of {@link ServiceInstance} or an empty list if no instances are found
     */
    public List<ServiceInstance> getInstancesByServiceName(String serviceName) {
        return discoveryClient.getInstances(serviceName);
    }

    /**
     * Retrieves the first available instance for a specific service.
     *
     * @param serviceName the name of the service to find
     * @return an {@link Optional} containing the first instance or empty if none are available
     */
    public Optional<ServiceInstance> getFirstInstance(String serviceName) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        return instances.isEmpty() ? Optional.empty() : Optional.of(instances.get(0));
    }

    /**
     * Fetches all the registered service names.
     *
     * @return a list of registered service names
     */
    public List<String> getAllServiceNames() {
        return discoveryClient.getServices();
    }

    /**
     * Prints service instance details for a given service name.
     *
     * @param serviceName the name of the service to inspect
     */
    public void printServiceInstances(String serviceName) {
        List<ServiceInstance> instances = getInstancesByServiceName(serviceName);

        if (instances.isEmpty()) {
            System.out.println("No instances found for service: " + serviceName);
        } else {
            System.out.println("Instances for service: " + serviceName);
            for (ServiceInstance instance : instances) {
                System.out.println(" - Instance ID: " + instance.getInstanceId());
                System.out.println("   URI: " + instance.getUri());
                System.out.println("   Host: " + instance.getHost());
                System.out.println("   Port: " + instance.getPort());
                System.out.println("   Metadata: " + instance.getMetadata());
            }
        }
    }
}
