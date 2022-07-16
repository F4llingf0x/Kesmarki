package hu.kesmarki.persons.controller;

import hu.kesmarki.persons.domain.Person;
import hu.kesmarki.persons.service.PersonService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class PersonController {



    private Scanner scanner = new Scanner(System.in);
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    public int askIntFromUser() {
        int userInt = scanner.nextInt();
        scanner.nextLine();
        return userInt;
    }

    public String askTextFromUser() {
        return scanner.nextLine();
    }

    public void personBuilder(Person person) {

        boolean personIsPresent = (person != null);
        if (!personIsPresent) {
            person = new Person();
        }

        System.out.print("Please enter the person's first name");
        if (personIsPresent) {
            System.out.print(", previous value: " + person.getFirstName());
        }
        System.out.println();
        person.setFirstName(askTextFromUser());

        System.out.print("Please enter the person's last name");
        if (personIsPresent) {
            System.out.print(", previous value: " + person.getLastName());
        }
        System.out.println();
        person.setLastName(askTextFromUser());

        System.out.print("Please enter the person's occupation");
        if (personIsPresent) {
            System.out.print(", previous value: " + person.getOccupation());
        }
        System.out.println();
        person.setOccupation(askTextFromUser());

        if (!personIsPresent) {
            System.out.println(personService.addPerson(person));
        } else {
            System.out.println(personService.modifyPerson(person));
        }
    }

    public Person findPersonById(int id) {
        return personService.findPersonById(id);
    }

    public List<Person> findPersonByName(String name) {
        return personService.findPersonByName(name);
    }

    public List<Person> findAllPeople() {
        return personService.findAllPeople();
    }

    public Person deletePerson(Person personToDelete) {
        return personService.deletePerson(personToDelete);
    }
}
