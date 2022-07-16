package hu.kesmarki.persons.ui;

import hu.kesmarki.persons.controller.PersonController;
import hu.kesmarki.persons.domain.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class PersonsUI {

    private Scanner scanner = new Scanner(System.in);

    private static final String NEW_LINE = "\r\n";
    private static final String TAB = "\t";

    public static final String PERSON_MENU_MESSAGE = "Please choose from the following options:" + NEW_LINE +
            TAB + "1. Add person" + NEW_LINE +
            TAB + "2. Modify person" + NEW_LINE +
            TAB + "3. Find person by id" + NEW_LINE +
            TAB + "4. Find people by name" + NEW_LINE +
            TAB + "5. List all people" + NEW_LINE +
            TAB + "6. Delete person" + NEW_LINE +
            TAB + "7. Back";

    public static final String FIND_PERSON_MESSAGE = "Please choose from the following options:" + NEW_LINE +
            TAB + "1. Find person by id" + NEW_LINE +
            TAB + "2. Find person by name" + NEW_LINE +
            TAB + "3. Back";

    int x = 0;

    private PersonController personController;

    public PersonsUI(PersonController personController) {
        this.personController = personController;
    }

    public int askIntFromUser() {
        int userInt = scanner.nextInt();
        scanner.nextLine();
        return userInt;
    }

    public String askTextFromUser() {
        return scanner.nextLine();
    }


    //TODO show addresses
    //TODO show contact to address

    public boolean personMenu(int value) {
        boolean isTerminated = false;
        switch (value) {
            case 1:
                personController.personBuilder(null);
                break;

            case 2:
                Person foundPerson = findPerson("modify");
                if (foundPerson != null) {
                    personController.personBuilder(foundPerson);
                }else {
                    System.out.println("Person has not been found");
                }
                break;

            case 3:
                System.out.println(findPersonById("find"));
                break;

            case 4:
                System.out.println(findPersonByName("find"));
                //TODO show addresses
                break;

            case 5:
                List<Person> people = personController.findAllPeople();
                System.out.println(people.size() + "person has been found");
                System.out.println(people);
                break;

            case 6:
                Person personToDelete = findPerson("delete");
                if (personToDelete != null) {
                    Person deletedPerson = personController.deletePerson(personToDelete);
                    System.out.println();
                    System.out.println(deletedPerson.getFirstName() + " " + deletedPerson.getLastName() +
                            " has been deleted");
                }
                break;

            case 7:
                isTerminated = true;
                break;
        }
        return isTerminated;
    }

    public Person findPerson(String purpose) {
        Person foundPerson = null;
        System.out.println(FIND_PERSON_MESSAGE);
        int x = askIntFromUser();
        switch (x) {
            case 2:
                List<Person> personList = findPersonByName(purpose);
                System.out.println(personList);
                if (personList.size() == 1) {
                    foundPerson = personList.get(0);
                    break;
                }
            case 1:
                foundPerson = findPersonById(purpose);
                break;
            case 3:
                break;
        }

        return foundPerson;
    }

    private Person findPersonById(String purpose) {
        System.out.println("Please enter the person's id to " + purpose);
        x = askIntFromUser();
        Person foundPerson = personController.findPersonById(x);
        return foundPerson;
    }

    private List<Person> findPersonByName(String purpose) {
        System.out.println("Please enter the exact first- or lastname to " + purpose + " person");
        String name = askTextFromUser();
        List<Person> personList = personController.findPersonByName(name);
        System.out.println();
        System.out.println(personList.size() + " person has been found:");
        System.out.println();
        return personList;
    }


}
