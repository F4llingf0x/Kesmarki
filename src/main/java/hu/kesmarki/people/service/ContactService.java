package hu.kesmarki.people.service;

import hu.kesmarki.people.domain.Contact;
import hu.kesmarki.people.repository.ContactRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ContactService {

    private ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    public Contact addContact(Contact contact) {
        return contactRepository.addContact(contact);
    }

    public Contact modifyContact(Contact contact) {
        return contactRepository.modifyContact(contact);
    }
}
