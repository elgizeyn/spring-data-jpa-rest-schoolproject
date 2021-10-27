package main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {StudentNotFoundRequestException.class})
    public ResponseEntity<Object> handleException(StudentNotFoundRequestException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        StudentNotFoundException studentNotFoundException = new StudentNotFoundException(e.getMessage(), e, badRequest, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(studentNotFoundException, badRequest);
    }
}
