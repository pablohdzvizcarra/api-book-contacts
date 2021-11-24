package jvm.pablohdz.apibookcontacts.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.http.HttpStatus;

import java.time.format.DateTimeFormatter;
import java.util.Map;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultResponse
{
    public static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
    protected String timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String reason;
    protected String message;
    protected String developerMessage;
    protected Map<?, ?> data;
}
