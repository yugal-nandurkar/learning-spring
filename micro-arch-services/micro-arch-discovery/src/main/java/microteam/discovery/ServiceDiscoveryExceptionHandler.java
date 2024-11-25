package microteam.discovery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Component to handle exceptions that occur during service discovery operations.
 */
@Component
public class ServiceDiscoveryExceptionHandler {

    // Logger to log exceptions
    private static final Logger logger = LoggerFactory.getLogger(ServiceDiscoveryExceptionHandler.class);

    /**
     * Handles exceptions that occur during service discovery operations.
     * This method logs the exception message, stack trace, and provides a user-friendly message.
     *
     * @param exception   The exception that occurred during service discovery
     * @param serviceName
     */
    public void handle(Exception exception, String serviceName) {
        // Log the exception with the error level
        logger.error("Error during service discovery: {}", exception.getMessage(), exception);

        // Add any custom recovery logic here if necessary
        // For example, notifying system administrators or triggering fallback mechanisms
        recoverFromException(exception);
    }

    /**
     * Custom recovery mechanism for handling exceptions in service discovery.
     * This could include triggering fallback methods, alerting administrators, or other strategies.
     *
     * @param exception The exception that occurred
     */
    private void recoverFromException(Exception exception) {
        // For example, alerting via email, SMS, or sending a notification to monitoring systems
        // In this case, we simply print a recovery message (In a real system, it could be more complex)
        logger.info("Attempting to recover from service discovery error...");

        // If there is a specific exception type, handle it differently
        if (exception instanceof ServiceDiscoveryTimeoutException) {
            logger.warn("Service discovery timed out, retrying...");
            // Implement retry logic or fallback strategy here
        } else if (exception instanceof ServiceNotFoundException) {
            logger.warn("Service not found, checking alternate services...");
            // Implement alternate service lookup or fallback strategy
        } else {
            logger.warn("Unknown exception occurred, taking default recovery action.");
            // Implement a general fallback strategy
        }

        // Further recovery steps can be added as needed
    }

    /**
     * A custom exception class to represent service discovery timeout errors.
     * This can be extended with more specific exceptions as needed.
     */
    public static class ServiceDiscoveryTimeoutException extends Exception {
        public ServiceDiscoveryTimeoutException(String message) {
            super(message);
        }
    }

    /**
     * A custom exception class to represent service not found errors during discovery.
     * This can be extended with more specific exceptions as needed.
     */
    public static class ServiceNotFoundException extends Exception {
        public ServiceNotFoundException(String message) {
            super(message);
        }
    }
}
