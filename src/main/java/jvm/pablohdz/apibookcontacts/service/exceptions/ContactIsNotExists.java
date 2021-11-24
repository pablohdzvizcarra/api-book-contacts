package jvm.pablohdz.apibookcontacts.service.exceptions;

public class ContactIsNotExists extends RuntimeException {
    public ContactIsNotExists(String message) {
        super(message);
    }
}
