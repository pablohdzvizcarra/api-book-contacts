package jvm.pablohdz.apibookcontacts.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;

import jvm.pablohdz.apibookcontacts.model.Contact;
import jvm.pablohdz.apibookcontacts.repository.ContactRepository;


public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent>
{
    private boolean ALREADY_SETUP = false;
    private final ContactRepository contactRepository;

    @Autowired
    public SetupDataLoader(ContactRepository contactRepository)
    {
        this.contactRepository = contactRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        if (ALREADY_SETUP)
            return;

        Contact james = new Contact();
        james.setName("james");
        james.setPhoneType("mobile");
        james.setPhoneNumber("8734892613");

        Contact bob = new Contact();
        bob.setPhoneNumber("8744905623");
        bob.setName("bob");
        bob.setPhoneType("home");
        contactRepository.save(james);
        contactRepository.save(bob);

        ALREADY_SETUP = true;
    }
}
