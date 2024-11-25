package microteam.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class Gateway {

    // Define a simple global filter for logging requests
    @Bean
    public GlobalFilter logRequestFilter() {
        return (exchange, chain) -> {
            System.out.println("Request path: " + exchange.getRequest().getURI());
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() ->
                            System.out.println("Response code: " + exchange.getResponse().getStatusCode())));
        };
    }

    // Example of a custom Gateway Filter
    @Bean
    public GatewayFilterFactory<CustomConfig> customGatewayFilterFactory() {
        return new GatewayFilterFactory<>() {
            @Override
            public GatewayFilter apply(CustomConfig config) {
                return (exchange, chain) -> {
                    System.out.println("Custom Gateway filter applied: " + config.getMessage());
                    return chain.filter(exchange);
                };
            }

            @Override
            public Class<CustomConfig> getConfigClass() {
                return CustomConfig.class;
            }
        };
    }

    // Custom configuration class for the Gateway Filter
    @Setter
    @Getter
    public static class CustomConfig {
        private String message;

    }
}
