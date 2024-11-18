package microteam.controllers;

import microteam.exceptions.UserNotFoundException;
import microteam.services.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice // Step 20
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),
                LocalDateTime.now(), request.getDescription(false), request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
     }

     // Step 21
     @ExceptionHandler(value = { UserNotFoundException.class })
     public final ResponseEntity<ErrorDetails> handleUserNotFoundExceptionException(Exception ex, WebRequest request) throws Exception {
         ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),
                 LocalDateTime.now(), request.getDescription(false), request.getDescription(false));

         return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
     }

     // UserResource: Step 22

    @Override // Step 26
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),
                LocalDateTime.now(),
               "Total Errors: " +ex.getErrorCount() + " First Error" +
                       ex.getFieldError().getDefaultMessage(),
                request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // ErrorDetails: Step 27
}
