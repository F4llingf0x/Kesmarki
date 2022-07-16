package hu.kesmarki.people.ui;

import hu.kesmarki.people.controller.ContactController;
import hu.kesmarki.people.controller.PersonController;
import hu.kesmarki.people.domain.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactUI {

    private static final String NEW_LINE = "\r\n";
    private static final String TAB = "\t";

    public static final String CONTACT_MENU_MESSAGE = "Please choose from the following options:" + NEW_LINE +
            TAB + "1. Add contact" + NEW_LINE +
            TAB + "2. Modify contact" + NEW_LINE +
            TAB + "3. Assign to or change address" + NEW_LINE +
            TAB + "4. Find contact by id" + NEW_LINE +
            TAB + "5. Find contact by person" + NEW_LINE +
            TAB + "6. Find contact by address" + NEW_LINE +
            TAB + "7. List all not assigned contacts" + NEW_LINE +
            TAB + "8. Delete contact" + NEW_LINE +
            TAB + "9. Back";

    public static final String FIND_CONTACT_MESSAGE = "Please choose from the following options:" + NEW_LINE +
            TAB + "1. Find contact by contact id" + NEW_LINE +
            TAB + "2. Find contact by person" + NEW_LINE +
            TAB + "3. Find contact by address" + NEW_LINE +
            TAB + "4. Back";

    int x = 0;

    private ContactController contactController;



    public boolean contactMenu(int value) {
        boolean isTerminated = false;
        switch (value) {
            case 1:
                contactController.contactBuilder(null);
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
                System.out.println(people.size() + " person has been found");
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


    public Person findContact(String purpose) {
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




}
