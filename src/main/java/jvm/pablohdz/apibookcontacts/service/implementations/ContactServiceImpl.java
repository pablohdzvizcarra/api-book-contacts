package jvm.pablohdz.apibookcontacts.service.implementations;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jvm.pablohdz.apibookcontacts.mapper.ContactMapper;
import jvm.pablohdz.apibookcontacts.model.Contact;
import jvm.pablohdz.apibookcontacts.model.ContactDto;
import jvm.pablohdz.apibookcontacts.model.ContactRequest;
import jvm.pablohdz.apibookcontacts.model.ContactRequestWitId;
import jvm.pablohdz.apibookcontacts.repository.ContactRepository;
import jvm.pablohdz.apibookcontacts.service.ContactService;
import jvm.pablohdz.apibookcontacts.service.exceptions.ContactIsNotExists;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Autowired
    public ContactServiceImpl(
            ContactRepository contactRepository,
            ContactMapper contactMapper
    ) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public ContactDto create(@NotNull ContactRequest request) {
        Contact contact = createContact(request);
        Contact contactSaved = saveContact(contact);
        return contactMapper.contactToContactDto(contactSaved);
    }

    @Override
    public Collection<ContactDto> read() {
        List<Contact> contactList = contactRepository.findAll();

        return contactList.stream()
                .map(contactMapper::contactToContactDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void delete(Long id) {
        doContactExist(id);
        contactRepository.deleteById(id);
    }

    @Override
    public ContactDto update(ContactRequestWitId request) {
        Long id = request.getId();
        doContactExist(id);

        return null;
    }

    private void doContactExist(Long id) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isEmpty())
            throw new ContactIsNotExists("The user with the id: " + id + " is not exist");
    }

    @NotNull
    private Contact saveContact(Contact contact) {
        try {
            return contactRepository.save(contact);
        } catch (Exception exception) {
            throw new IllegalArgumentException("you try save a contact, but the phone number " +
                    "already is registered, remember phone numbers be uniques");
        }
    }

    @NotNull
    private Contact createContact(@NotNull ContactRequest request) {
        Contact contact = new Contact();
        contact.setPhoneNumber(request.getPhoneNumber());
        contact.setPhoneType(request.getPhoneType());
        contact.setName(request.getUsername());
        return contact;
    }
}
