package microteam.tracer;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.springframework.stereotype.Component;

@Component
public class TracingUtils {

    private final Tracer tracer;

    public TracingUtils(Tracer tracer) {
        this.tracer = tracer;
    }

    public Span startSpan(String spanName) {
        return tracer.spanBuilder(spanName).startSpan();
    }

    public void endSpan(Span span) {
        if (span != null) {
            span.end();
        }
    }
}
