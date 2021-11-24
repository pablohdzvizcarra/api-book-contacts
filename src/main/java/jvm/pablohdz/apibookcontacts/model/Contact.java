package jvm.pablohdz.apibookcontacts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "contact")
public class Contact extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private Long id;

    @Column(name = "contact_name", nullable = false)
    private String name;

    @Column(name = "contact_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "contact_type", nullable = false)
    private String phoneType;

    public Contact()
    {
    }
}
