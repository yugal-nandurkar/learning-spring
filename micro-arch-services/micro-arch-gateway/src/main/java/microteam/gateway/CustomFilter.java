package microteam.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Log incoming requests
            System.out.println("CustomFilter: Processing request for URI: " + exchange.getRequest().getURI());
            // Add custom header
            exchange.getResponse().getHeaders().add(HttpHeaders.SERVER, "SpringCloudGateway");
            return chain.filter(exchange);
        };
    }

    public static class Config {
        // Configuration properties for the filter can be added here
    }
}
