package jvm.pablohdz.apibookcontacts.service.implementations;

import net.bytebuddy.implementation.bytecode.Throw;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jvm.pablohdz.apibookcontacts.mapper.ContactMapper;
import jvm.pablohdz.apibookcontacts.model.Contact;
import jvm.pablohdz.apibookcontacts.model.ContactDto;
import jvm.pablohdz.apibookcontacts.model.ContactRequest;
import jvm.pablohdz.apibookcontacts.repository.ContactRepository;
import jvm.pablohdz.apibookcontacts.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService
{
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Autowired
    public ContactServiceImpl(
            ContactRepository contactRepository,
            ContactMapper contactMapper
    )
    {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public ContactDto save(@NotNull ContactRequest request)
    {
        Contact contact = createContact(request);
        Contact contactSaved = saveContact(contact);
        return contactMapper.contactToContactDto(contactSaved);
    }

    @NotNull
    private Contact saveContact(Contact contact)
    {
        try
        {
            return contactRepository.save(contact);
        } catch (Exception exception)
        {
            throw new IllegalArgumentException("you try save a contact, but the phone number " +
                    "already is registered, remember phone numbers be uniques");
        }
    }

    @NotNull
    private Contact createContact(@NotNull ContactRequest request)
    {
        Contact contact = new Contact();
        contact.setPhoneNumber(request.getPhoneNumber());
        contact.setPhoneType(request.getPhoneType());
        contact.setName(request.getUsername());
        return contact;
    }
}
