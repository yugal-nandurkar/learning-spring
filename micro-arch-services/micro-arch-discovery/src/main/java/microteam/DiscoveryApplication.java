package microteam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
        basePackages = "microteam",
        excludeFilters = @ComponentScan.Filter(
                type = org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE,
                value = microteam.configuration.ServiceDiscovery.class // Exclude one of the conflicting classes
        )
)
public class DiscoveryApplication {

    /**
     * Main method that serves as the entry point for the Spring Boot application.
     * It triggers the launch of the Spring application context.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApplication.class, args);
    }

    /**
     * CommandLineRunner bean that runs a piece of code after the Spring Boot application has started.
     * This method allows us to execute logic right after the context is initialized, such as:
     * - Interacting with the discovery service
     * - Verifying if the application registers correctly
     * - Testing service discovery functionality
     *
     * @param context The application context
     * @return CommandLineRunner that runs at startup
     */
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {
            System.out.println("Spring Boot Application started successfully!");

            // Example: Get the ServiceDiscovery bean and use it for testing service discovery
            // Uncomment and replace with the correct bean name if needed
            // ServiceDiscovery discoveryService = context.getBean("servicesServiceDiscovery", ServiceDiscovery.class);
            // discoveryService.discoverServices("example-service");

            // Print out a message indicating that service discovery is running
            System.out.println("Running the Service Discovery Example...");
        };
    }

    /**
     * Runnable bean for service registration logic, which will execute on startup.
     * This can be used to register the application with a service registry like Consul.
     * For instance, you can dynamically register the application with Consul at startup.
     *
     * @return Runnable that performs service registration logic at application startup
     */
    @Bean
    public Runnable serviceRegistrationRunnable() {
        return () -> {
            // Example: Dynamically registering the service with Consul
            // This would typically involve calling the ServiceRegistration class or similar components.
            System.out.println("Service registration logic would go here...");

            // Sample mock registration process
            // Uncomment and replace with the correct bean usage if needed
            // ServiceRegistrationService serviceRegistrationService = context.getBean(ServiceRegistrationService.class);
            // serviceRegistrationService.registerService();
        };
    }
}
