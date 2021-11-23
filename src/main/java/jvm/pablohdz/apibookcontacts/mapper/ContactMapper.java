package jvm.pablohdz.apibookcontacts.mapper;

import org.mapstruct.Mapper;

import jvm.pablohdz.apibookcontacts.model.Contact;
import jvm.pablohdz.apibookcontacts.model.ContactDto;

@Mapper(componentModel = "spring")
public interface ContactMapper
{
    ContactDto contactToContactDto(Contact contact);
}
