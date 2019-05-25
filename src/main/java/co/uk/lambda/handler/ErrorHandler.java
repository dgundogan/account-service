package co.uk.lambda.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception e) {
        ErrorResponse errors = new ErrorResponse();
        errors.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errors.setMessage(e.getMessage());
        log.error("Error:", e);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
