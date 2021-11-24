package jvm.pablohdz.apibookcontacts.service;

import java.util.Collection;

import jvm.pablohdz.apibookcontacts.model.ContactDto;
import jvm.pablohdz.apibookcontacts.model.ContactRequest;

public interface ContactService {
    ContactDto create(ContactRequest request);

    Collection<ContactDto> read();

    /**
     * Delete a contact identified by id, if the contact does not exist return Exception.
     *
     * @param id the id of the contact to eliminated
     */
    void delete(Long id);
}
