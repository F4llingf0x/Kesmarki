package hu.kesmarki.persons.repository;

import hu.kesmarki.persons.domain.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AddressRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Address addAddress(Address address) {
        entityManager.persist(address);
        return address;
    }

    public Address modifyAddress(Address address) {
        return entityManager.merge(address);
    }
}
