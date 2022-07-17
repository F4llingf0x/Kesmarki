package hu.kesmarki.people.service;

import hu.kesmarki.people.domain.Address;
import hu.kesmarki.people.domain.Person;
import hu.kesmarki.people.repository.PersonRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {

    private PersonRepository personRepository;
    private AddressService addressService;

    public PersonService(PersonRepository personRepository, AddressService addressService) {
        this.personRepository = personRepository;
        this.addressService = addressService;
    }

    public Person addPerson(Person person) {
        Person savedPerson = personRepository.addPerson(person);
        return savedPerson;
    }

    public Person findPersonById(int id) {
        Optional<Person> foundPerson = personRepository.findPersonById(id);
        if (foundPerson.isPresent() && !foundPerson.get().isDeleted()) {
            return foundPerson.get();
        } else {
            System.out.println("Person not found");
            return null;
        }
    }

    public List<Person> findPersonByName(String name) {
        return personRepository.findPersonByName(name);
    }

    public Person modifyPerson(Person person) {
        return personRepository.modifyPerson(person);
    }

    public List<Person> findAllPeople() {
        return personRepository.findAllPeople();
    }

    public Person deletePerson(Person personToDelete, boolean deleteCascadeAddress, boolean deleteCascadeContact) {
        personToDelete.setDeleted(true);
        for (Address address : personToDelete.getAddress()) {
            if (deleteCascadeAddress) {
                addressService.deleteAddress(address, deleteCascadeContact);
            } else {
                address.setPerson(null);
                addressService.modifyAddress(address);

            }
        }
        return personRepository.modifyPerson(personToDelete);
    }
}
