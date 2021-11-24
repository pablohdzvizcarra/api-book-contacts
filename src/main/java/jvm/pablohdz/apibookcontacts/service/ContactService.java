package jvm.pablohdz.apibookcontacts.service;

import java.util.Collection;

import jvm.pablohdz.apibookcontacts.model.ContactDto;
import jvm.pablohdz.apibookcontacts.model.ContactRequest;

public interface ContactService
{
    ContactDto create(ContactRequest request);

    Collection<ContactDto> read();
}
