package microteam.tracer;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Get the current span
        Span span = Span.current();
        if (span != null) {
            // Record exception details in the span
            span.recordException(ex);
            // Set span status to ERROR
            span.setStatus(StatusCode.ERROR, "Unhandled exception occurred");
        }
        return ResponseEntity.internalServerError().body("Error: " + ex.getMessage());
    }
}
