package jvm.pablohdz.apibookcontacts.service.exceptionshandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import jvm.pablohdz.apibookcontacts.model.DefaultResponse;
import jvm.pablohdz.apibookcontacts.service.exceptions.ContactIsNotExists;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value =
            {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<DefaultResponse> handleConflict(
            RuntimeException ex
    ) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(DefaultResponse.builder()
                .timeStamp(LocalDateTime.now().format(DefaultResponse.formatter))
                .developerMessage("exception because try save duplicated data")
                .data(Map.of("error", ex.getMessage()))
                .message("exception occurred")
                .status(HttpStatus.CONFLICT)
                .reason(HttpStatus.CONFLICT.toString())
                .statusCode(HttpStatus.CONFLICT.value())
                .build());
    }

    @ExceptionHandler(value = {ContactIsNotExists.class})
    protected ResponseEntity<DefaultResponse> handleContactIsNotExists(ContactIsNotExists ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DefaultResponse.builder()
                .timeStamp(LocalDateTime.now().format(DefaultResponse.formatter))
                .developerMessage("exception because try delete contact that is not exist")
                .data(Map.of("error", ex.getMessage()))
                .message("exception occurred")
                .status(HttpStatus.BAD_REQUEST)
                .reason(HttpStatus.BAD_REQUEST.toString())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build());
    }
}
