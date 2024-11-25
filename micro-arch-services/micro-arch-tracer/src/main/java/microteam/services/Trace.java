package microteam.services;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import org.springframework.stereotype.Service;

@Service
public class Trace{

    private final Tracer tracer;

    public Trace(OpenTelemetrySdk openTelemetrySdk) {
        this.tracer = openTelemetrySdk.getTracer("tracing-service");
    }

    public String traceMethod(String input) {
        // Start a span
        Span span = tracer.spanBuilder("traceMethod")
                .setSpanKind(SpanKind.SERVER)
                .startSpan();

        try {
            // Simulate some processing logic
            span.setAttribute("input", input);
            String processedOutput = "Processed: " + input.toUpperCase();
            span.setAttribute("output", processedOutput);

            return processedOutput;
        } finally {
            span.end(); // End the span
        }
    }
}
