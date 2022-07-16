package hu.kesmarki.people.repository;

import hu.kesmarki.people.domain.Address;
import hu.kesmarki.people.domain.Contact;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

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

    public Optional<Contact> findContactById(int x) {
        return Optional.ofNullable(entityManager.find(Contact.class, x));
    }

    public List<Contact> findAllUnassignedContact() {
        return entityManager.createQuery("SELECT c FROM Contact c " +
                        "WHERE c.isDeleted = false " +
                        "AND c.address IS NULL " +
                        "ORDER BY c.number ASC ", Contact.class)
                .getResultList();
    }
}
