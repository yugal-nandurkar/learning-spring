package microteam.controller;

import io.opentelemetry.api.trace.Span;
import microteam.tracer.TracingUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Trace {

    private final TracingUtils tracingUtils;

    public Trace(TracingUtils tracingUtils) {
        this.tracingUtils = tracingUtils;
    }

    @GetMapping("/trace")
    public String trace(@RequestParam String input) {
        Span span = tracingUtils.startSpan("traceEndpoint");
        try {
            String result = "Traced: " + input.toUpperCase();
            span.setAttribute("input", input);
            span.setAttribute("result", result);
            return result;
        } finally {
            tracingUtils.endSpan(span);
        }
    }
}
