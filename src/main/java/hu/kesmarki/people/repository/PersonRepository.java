package hu.kesmarki.people.repository;

import hu.kesmarki.people.domain.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Person addPerson(Person person) {
        entityManager.persist(person);
        return person;
    }

    public Optional<Person> findPersonById(int id) {
        return Optional.ofNullable(entityManager.find(Person.class, id));
    }

    public List<Person> findPersonByName(String name) {
        return entityManager.createQuery("SELECT p FROM Person p " +
                        "WHERE p.isDeleted = false " +
                        "AND (p.firstName LIKE :value OR p.lastName LIKE :value)" +
                        "ORDER BY p.lastName, p.firstName DESC", Person.class)
                .setParameter("value", name)
                .getResultList();
    }

    public Person modifyPerson(Person person) {
        return entityManager.merge(person);
    }


    public List<Person> findAllPeople() {
        return entityManager.createQuery("SELECT p FROM Person p " +
                "WHERE p.isDeleted = false", Person.class)
                .getResultList();
    }


}
