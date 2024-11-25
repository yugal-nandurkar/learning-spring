package microteam;

import microteam.discovery.ConsulService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConsulServiceTest {

    @Test
    void testGetInstancesByServiceName() {
        DiscoveryClient mockClient = mock(DiscoveryClient.class);
        ConsulService serviceDiscovery = new ConsulService(mockClient);

        ServiceInstance instance = Mockito.mock(ServiceInstance.class);
        when(mockClient.getInstances("test-service")).thenReturn(Collections.singletonList(instance));

        List<ServiceInstance> result = serviceDiscovery.getInstancesByServiceName("test-service");
        assertEquals(1, result.size());
    }
}

