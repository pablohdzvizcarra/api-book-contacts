package jvm.pablohdz.apibookcontacts.service;

import java.util.Collection;

import jvm.pablohdz.apibookcontacts.model.ContactDto;
import jvm.pablohdz.apibookcontacts.model.ContactRequest;
import jvm.pablohdz.apibookcontacts.model.ContactRequestWitId;

public interface ContactService {
    ContactDto create(ContactRequest request);

    Collection<ContactDto> read();

    /**
     * Delete a contact identified by id, if the contact does not exist return Exception.
     *
     * @param id the id of the contact to eliminated
     */
    void delete(Long id);

    /**
     * Update a contact with the provided data, if the contact is not exists before try update
     * thrown a exception
     *
     * @param request the data used to update
     * @return the updated data
     */
    ContactDto update(ContactRequestWitId request);
}
