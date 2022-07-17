package hu.kesmarki.people.service;

import hu.kesmarki.people.domain.Contact;
import hu.kesmarki.people.repository.ContactRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public Contact findContactById(int x) {
        Optional<Contact> foundContact = contactRepository.findContactById(x);
        if (foundContact.isPresent() && !foundContact.get().isDeleted()) {
            return foundContact.get();
        }else {
            System.out.println("Contact not found");
            return null;
        }
    }

    public List<Contact> findAllUnassignedContact() {
        return contactRepository.findAllUnassignedContact();
    }

    public Contact deleteContact(Contact contactToDelete) {
        contactToDelete.setDeleted(true);
        return contactRepository.modifyContact(contactToDelete);
    }
}
