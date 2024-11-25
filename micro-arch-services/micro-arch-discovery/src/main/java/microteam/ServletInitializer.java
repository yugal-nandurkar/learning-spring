package microteam;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * Configures the application when running in a servlet container.
     *
     * This method is called when the application is deployed to a traditional servlet container
     * (like Tomcat) instead of running as a standalone Spring Boot application.
     *
     * @param application the {@link SpringApplicationBuilder} used to configure the application
     * @return the configured {@link SpringApplicationBuilder}
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // Set the primary source of the application
        return application.sources(DiscoveryApplication.class);
    }
}
