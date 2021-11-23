package jvm.pablohdz.apibookcontacts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jvm.pablohdz.apibookcontacts.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>
{
}
