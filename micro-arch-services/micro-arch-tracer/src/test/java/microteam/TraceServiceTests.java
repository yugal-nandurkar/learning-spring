package microteam;

import io.opentelemetry.sdk.OpenTelemetrySdk;
import microteam.services.Trace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TraceServiceTests {

    private Trace traceService;

    @BeforeEach
    void setUp() {
        // Use a mock OpenTelemetrySdk for testing
        OpenTelemetrySdk openTelemetrySdk = OpenTelemetrySdk.builder().build();
        traceService = new Trace(openTelemetrySdk);
    }

    @Test
    void testTraceMethod() {
        // Input and expected output
        String input = "test-input";
        String expectedOutput = "Processed: TEST-INPUT";

        // Assert the output matches expected
        String actualOutput = traceService.traceMethod(input);
        assertEquals(expectedOutput, actualOutput);
    }
}
