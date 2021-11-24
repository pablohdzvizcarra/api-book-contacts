package jvm.pablohdz.apibookcontacts.service.exceptionshandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;

import jvm.pablohdz.apibookcontacts.model.DefaultResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(value =
            {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<DefaultResponse> handleConflict(
            RuntimeException ex, WebRequest request
    )
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(DefaultResponse.builder()
                .timeStamp(LocalDateTime.now())
                .developerMessage("exception because try save duplicated data")
                .data(Map.of("error", ex.getMessage()))
                .message("exception occurred")
                .status(HttpStatus.CONFLICT)
                .reason(HttpStatus.CONFLICT.toString())
                .statusCode(HttpStatus.CONFLICT.value())
                .build());
    }
}
