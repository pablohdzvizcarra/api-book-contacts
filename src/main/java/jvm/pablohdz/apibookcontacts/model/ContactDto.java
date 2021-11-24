package jvm.pablohdz.apibookcontacts.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto
{
    private Long id;
    private String name;
    private String phoneNumber;
    private String phoneType;
    private String createdAt;
    private String updatedAt;
}
