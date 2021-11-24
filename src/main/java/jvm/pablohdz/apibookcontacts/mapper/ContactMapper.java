package jvm.pablohdz.apibookcontacts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import jvm.pablohdz.apibookcontacts.model.Contact;
import jvm.pablohdz.apibookcontacts.model.ContactDto;

@Mapper(componentModel = "spring")
public interface ContactMapper
{
    @Mapping(target = "createdAt", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(target = "updatedAt", dateFormat = "dd-MM-yyyy HH:mm")
    ContactDto contactToContactDto(Contact contact);
}
