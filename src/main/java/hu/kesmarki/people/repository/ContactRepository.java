package hu.kesmarki.people.repository;

import hu.kesmarki.people.domain.Contact;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ContactRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Contact addContact(Contact contact) {
        entityManager.persist(contact);
        return contact;
    }

    public Contact modifyContact(Contact contact) {
        return entityManager.merge(contact);
    }
}
