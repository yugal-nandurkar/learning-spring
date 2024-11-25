package microteam.services;

import microteam.discovery.ServiceDiscoveryExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceDiscovery {

    private final DiscoveryClient discoveryClient;
    private final ServiceDiscoveryExceptionHandler exceptionHandler;

    @Autowired
    public ServiceDiscovery(DiscoveryClient discoveryClient, ServiceDiscoveryExceptionHandler exceptionHandler) {
        this.discoveryClient = discoveryClient;
        this.exceptionHandler = exceptionHandler;
    }

    /**
     * Discovers the services registered under the given service name.
     *
     * @param serviceName the name of the service to discover
     * @return a list of {@link ServiceInstance} or an empty list in case of an error
     */
    public List<ServiceInstance> discoverServices(String serviceName) {
        try {
            return discoveryClient.getInstances(serviceName);
        } catch (Exception e) {
            exceptionHandler.handle(e, serviceName); // Log error with the service name for context
            return List.of(); // Return an empty list if there's an error
        }
    }
}
