package hu.kesmarki.people.repository;

import hu.kesmarki.people.domain.Address;
import hu.kesmarki.people.domain.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

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

    public Optional<Address> findAddressById(int x) {
        return Optional.ofNullable(entityManager.find(Address.class, x));
//        return Optional.ofNullable(entityManager.createQuery("SELECT a FROM Address a " +
//                "WHERE a.id = :value ",Address.class)
//                .setParameter("value", x)
//                .getResultList().get(0));
    }

    public List<Address> findNotAssignedAddresses() {
        return entityManager.createQuery("SELECT a FROM Address a " +
                        "WHERE a.isDeleted = false " +
                        "AND a.person IS NULL " +
                        "ORDER BY a.country, a.county, a.streetName, a.houseNumber ASC ", Address.class)
                .getResultList();
    }
}
