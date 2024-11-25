package microteam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GatewayApplicationTests {

    @Autowired
    private WebTestClient webTestClient; // Used for testing web endpoints

    @Autowired
    private RouteDefinitionLocator routeDefinitionLocator; // To validate configured routes

    /**
     * Test to ensure the application context loads successfully.
     */
    @Test
    void contextLoads() {
        assertThat(webTestClient).isNotNull();
        assertThat(routeDefinitionLocator).isNotNull();
    }

    /**
     * Test the `/gateway/status` endpoint to verify if it returns the correct response.
     */
    @Test
    void testGatewayStatusEndpoint() {
        webTestClient.get()
                .uri("/gateway/status")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response -> assertThat(response).isEqualTo("Gateway is up and running!"));
    }

    /**
     * Test if the gateway routes are loaded correctly from application.yml.
     */
    @Test
    void testRoutesAreConfigured() {
        var routes = routeDefinitionLocator.getRouteDefinitions().collectList().block();

        assertThat(routes).isNotNull();
        assertThat(routes).anyMatch(route -> "service-route".equals(route.getId())); // Verify the "service-route" ID exists
    }

    /**
     * Test to ensure a non-existing route returns 404.
     */
    @Test
    void testNonExistingRoute() {
        webTestClient.get()
                .uri("/non-existing-path")
                .exchange()
                .expectStatus().isNotFound();
    }

    /**
     * Test the gateway's ability to forward requests to a downstream service.
     * Note: This test assumes the downstream service is running and available.
     */
    @Test
    void testRequestForwardingToService() {
        webTestClient.get()
                .uri("/api/service/test") // Adjust based on actual routing
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response -> assertThat(response).contains("Expected response from downstream service"));
    }
}
