package jvm.pablohdz.apibookcontacts.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import jvm.pablohdz.apibookcontacts.model.ContactDto;
import jvm.pablohdz.apibookcontacts.model.ContactRequest;
import jvm.pablohdz.apibookcontacts.model.DefaultResponse;
import jvm.pablohdz.apibookcontacts.service.ContactService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController
{
    private final ContactService contactService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> save(@Valid @RequestBody ContactRequest request)
    {
        ContactDto data = contactService.save(request);

        return ResponseEntity.ok(DefaultResponse.builder()
                .timeStamp(LocalDateTime.now().format(DefaultResponse.formatter))
                .data(Map.of("contact", data))
                .message("contact saved")
                .developerMessage("the contact is created successfully")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build()
        );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception
    )
    {
        HashMap<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError ->
                        errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(DefaultResponse.builder()
                .timeStamp(LocalDateTime.now().format(DefaultResponse.formatter))
                .developerMessage("exception occurred when try validate data from the request")
                .data(Map.of("error", errors))
                .message("validations errors")
                .status(HttpStatus.CONFLICT)
                .reason(HttpStatus.CONFLICT.toString())
                .statusCode(HttpStatus.CONFLICT.value())
                .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<Map<String, String>> handleConstrainsViolationException(
            ConstraintViolationException exception
    )
    {
        Map<String, String> errors = new HashMap<>();
        exception.getConstraintViolations().forEach(constraintViolation ->
        {
            errors.put("message", constraintViolation.getMessage());
            errors.put("path", constraintViolation.getPropertyPath().toString());
        });
        return ResponseEntity.ok(errors);
    }

}
