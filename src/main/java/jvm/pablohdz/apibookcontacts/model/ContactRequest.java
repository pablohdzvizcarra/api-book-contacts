package jvm.pablohdz.apibookcontacts.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequest
{
    @NotNull
    @Size(min = 3, max = 55, message = "the name must be greater than 3 characters and not null")
    private String username;

    @Pattern(
            regexp = "[8][0-9]{10}",
            message = "the number must be valid to Mexico format example: 8711001133"
    )
    private String phoneNumber;

    @NotNull
    @NotBlank
    private String phoneType;
}
