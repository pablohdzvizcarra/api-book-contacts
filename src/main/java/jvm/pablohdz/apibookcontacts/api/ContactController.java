package jvm.pablohdz.apibookcontacts.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import jvm.pablohdz.apibookcontacts.model.ContactDto;
import jvm.pablohdz.apibookcontacts.model.ContactRequest;
import jvm.pablohdz.apibookcontacts.model.DefaultResponse;
import jvm.pablohdz.apibookcontacts.service.ContactService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
@Validated
public class ContactController {
    private final ContactService contactService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DefaultResponse> save(@Valid @RequestBody ContactRequest request) {
        ContactDto data = contactService.create(request);

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

    @GetMapping
    public ResponseEntity<DefaultResponse> read() {
        Collection<ContactDto> dtos = contactService.read();

        return ResponseEntity.ok(DefaultResponse.builder()
                .timeStamp(LocalDateTime.now().format(DefaultResponse.formatter))
                .data(Map.of("contacts", dtos))
                .message("contacts retrieved")
                .developerMessage("given a list of all contacts")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") @Min(1) Long id) {
        return ResponseEntity.ok("it's works");
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception
    ) {
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
    ResponseEntity<DefaultResponse> handleConstrainsViolationException(
            ConstraintViolationException exception
    ) {
        Map<String, String> errors = new HashMap<>();
        exception.getConstraintViolations().forEach(constraintViolation ->
        {
            errors.put("message", constraintViolation.getMessage());
            errors.put("path", constraintViolation.getPropertyPath().toString());
        });
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(DefaultResponse.builder()
                .timeStamp(LocalDateTime.now().format(DefaultResponse.formatter))
                .developerMessage("exception occurred when try validate path variables from the " +
                        "request")
                .data(Map.of("error", errors))
                .message("validations path variables errors")
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .reason(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build());
    }

}
