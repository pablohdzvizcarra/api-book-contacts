package jvm.pablohdz.apibookcontacts.service;

import jvm.pablohdz.apibookcontacts.model.ContactDto;
import jvm.pablohdz.apibookcontacts.model.ContactRequest;

public interface ContactService
{
    ContactDto save(ContactRequest request);
}
