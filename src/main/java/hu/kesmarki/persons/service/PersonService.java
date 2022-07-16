package hu.kesmarki.persons.service;

import hu.kesmarki.persons.domain.Person;
import hu.kesmarki.persons.repository.PersonRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person addPerson(Person person){
        Person savedPerson = personRepository.addPerson(person);
        return savedPerson;
    }

    public Person findPersonById(int id){
        return personRepository.findPersonById(id).get();
        //TODO if not deleted
        //TODO exceptionhandling
    }

    public List<Person> findPersonByName(String name){
        return personRepository.findPersonByName(name);
    }

    public Person modifyPerson(Person person) {
        return personRepository.modifyPerson(person);
    }

    public List<Person> findAllPeople() {
        return personRepository.findAllPeople();
    }

    public Person deletePerson(Person personToDelete) {
        personToDelete.setDeleted(true);
        return personRepository.modifyPerson(personToDelete);
    }
}
