package microteam.services;

import org.springframework.cloud.consul.serviceregistry.ConsulRegistration;
import org.springframework.cloud.consul.serviceregistry.ConsulServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ServiceRegistration {

    private static final Logger logger = LoggerFactory.getLogger(ServiceRegistration.class);

    private final ConsulServiceRegistry serviceRegistry;
    private final ConsulRegistration consulRegistration;

    public ServiceRegistration(ConsulServiceRegistry serviceRegistry, ConsulRegistration consulRegistration) {
        this.serviceRegistry = serviceRegistry;
        this.consulRegistration = consulRegistration;
    }

    /**
     * Registers the current service instance with Consul dynamically.
     */
    public void registerService() {
        try {
            serviceRegistry.register(consulRegistration);
            logger.info("Service registered dynamically: {}", consulRegistration.getServiceId());
        } catch (Exception e) {
            logger.error("Error during service registration: {}", e.getMessage(), e);
        }
    }

    /**
     * Deregisters the current service instance from Consul.
     */
    public void deregisterService() {
        try {
            serviceRegistry.deregister(consulRegistration);
            logger.info("Service deregistered: {}", consulRegistration.getServiceId());
        } catch (Exception e) {
            logger.error("Error during service deregistration: {}", e.getMessage(), e);
        }
    }
}
