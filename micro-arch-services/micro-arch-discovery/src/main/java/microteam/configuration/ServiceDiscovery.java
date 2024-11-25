package microteam.configuration;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.serviceregistry.ConsulAutoServiceRegistration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ServiceDiscovery {

    private final DiscoveryClient discoveryClient;
    private final ConsulAutoServiceRegistration consulServiceRegistration;
    private final ConsulDiscoveryProperties consulDiscoveryProperties;

    public ServiceDiscovery(DiscoveryClient discoveryClient,
                            ConsulAutoServiceRegistration consulServiceRegistration,
                            ConsulDiscoveryProperties consulDiscoveryProperties) {
        this.discoveryClient = discoveryClient;
        this.consulServiceRegistration = consulServiceRegistration;
        this.consulDiscoveryProperties = consulDiscoveryProperties;
    }

    @PostConstruct
    public void init() {
        // Initialization logic
        System.out.println("ServiceDiscovery initialized.");
    }

    @PreDestroy
    public void cleanup() {
        // Cleanup logic
        System.out.println("ServiceDiscovery cleaned up.");
    }

    public List<ServiceInstance> getServiceInstances(String serviceName) {
        return discoveryClient.getInstances(serviceName);
    }

    public void registerService() {
        consulServiceRegistration.start();
    }

    public void deregisterService() {
        consulServiceRegistration.stop();
    }

    @Bean
    public ConsulDiscoveryProperties configureConsulDiscovery() {
        consulDiscoveryProperties.setPreferIpAddress(true);
        consulDiscoveryProperties.setHealthCheckPath("/actuator/health");
        consulDiscoveryProperties.setInstanceId("custom-service-id");
        return consulDiscoveryProperties;
    }
}
