package microteam.services;

import java.time.LocalDateTime;

//Step 19
public class ErrorDetails {
    private String message;
    private LocalDateTime timestamp;
    private String stackTrace;
    private String description; // Step 27

    // Hello: Step 28

    public ErrorDetails(String message, LocalDateTime timestamp, String stackTrace, String description) {
        this.message = message;
        this.timestamp = timestamp;
        this.stackTrace = stackTrace;
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public String getDescription() {
        return description;
    }

    // CustomizedResponseEntityExceptionHandler: Step 20
}
