package microteam;

import microteam.gateway.CustomFilter;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

class CustomFilterTests {

    @Test
    void testCustomFilter() {
        CustomFilter filter = new CustomFilter();
        ServerWebExchange exchange = mock(ServerWebExchange.class);
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        ServerHttpResponse response = mock(ServerHttpResponse.class);
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        when(exchange.getRequest()).thenReturn(request);
        when(exchange.getResponse()).thenReturn(response);
        when(chain.filter(exchange)).thenReturn(Mono.empty());

        filter.apply(new CustomFilter.Config()).filter(exchange, chain);

        verify(request, times(1)).getURI();
        verify(chain, times(1)).filter(exchange);
    }
}
