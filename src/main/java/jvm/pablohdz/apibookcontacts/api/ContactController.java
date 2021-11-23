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

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import jvm.pablohdz.apibookcontacts.model.ContactRequest;

@RestController
@RequestMapping("/api/contact")
public class ContactController
{

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody ContactRequest request)
    {
        return ResponseEntity.ok(request);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException exception)
    {
        HashMap<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError ->
                        errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return errors;
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
