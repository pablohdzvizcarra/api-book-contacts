package jvm.pablohdz.apibookcontacts.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jvm.pablohdz.apibookcontacts.mapper.ContactMapper;
import jvm.pablohdz.apibookcontacts.model.Contact;
import jvm.pablohdz.apibookcontacts.model.ContactDto;
import jvm.pablohdz.apibookcontacts.model.ContactRequest;
import jvm.pablohdz.apibookcontacts.repository.ContactRepository;

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
        Contact contact = new Contact();
        contact.setPhoneNumber(request.getPhoneNumber());
        contact.setPhoneType(request.getPhoneType());
        contact.setName(request.getUsername());

        Contact contactSaved = contactRepository.save(contact);

        return contactMapper.contactToContactDto(contactSaved);
    }
}
