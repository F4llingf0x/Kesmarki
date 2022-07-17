package hu.kesmarki.people.ui;

import hu.kesmarki.people.controller.CommonCommands;
import hu.kesmarki.people.controller.PersonController;
import hu.kesmarki.people.domain.Person;
import lombok.With;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class PersonUI extends CommonCommands {

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

    public PersonUI(PersonController personController) {
        this.personController = personController;
    }

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
                }
                break;

            case 3:
                foundPerson = findPersonById("find");
                if (foundPerson != null) {
                    System.out.println(foundPerson);
                }
                break;

            case 4:
                List<Person> personList = findPersonByName("find");
                if (!personList.isEmpty()) {
                    System.out.println(personList);
                }
                break;

            case 5:
                List<Person> people = personController.findAllPeople();
                System.out.println(people.size() + " person has been found");
                System.out.println(people);
                break;

            case 6:
                Person personToDelete = findPerson("delete");
                boolean deleteCascadeContact = false;
                boolean deleteCascadeAddress = false;
                if (personToDelete != null) {
                    System.out.println();
                    System.out.println("Would you like to delete the corresponding addresses? (Y/N)");
                    String text = null;
                    while (text == null) {
                        text = askYNFromUser();
                        if (text != null && text.equalsIgnoreCase("y")) {
                            deleteCascadeAddress = true;
                        }
                    }
                    System.out.println();
                    System.out.println("Would you like to delete the corresponding contacts? (Y/N)");
                    text = null;
                    while (text == null) {
                        text = askYNFromUser();
                        if (text != null && text.equalsIgnoreCase("y")) {
                            deleteCascadeContact = true;
                        }
                    }

                    Person deletedPerson = personController.deletePerson(
                            personToDelete,
                            deleteCascadeAddress,
                            deleteCascadeContact
                    );
                    System.out.println();
                    System.out.print(deletedPerson.getFirstName() + " " + deletedPerson.getLastName() +
                            " has been deleted");
                    if (deleteCascadeAddress){
                        System.out.print(", with addresses");
                    }
                    if (deleteCascadeContact) {
                        System.out.print(", with contacts");
                    }
                    System.out.println();
                }
                break;

            case 7:
                isTerminated = true;
                break;
            default:
                System.out.println();
                System.out.print("Invalid option");
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
                if (personList.size() == 1) {
                    foundPerson = personList.get(0);
                    System.out.println(foundPerson);
                    break;
                } else if (personList.isEmpty()) {
                    break;
                } else {
                    System.out.println(personList);
                }
            case 1:
                foundPerson = findPersonById(purpose);
                break;
            case 3:
                break;
            default:
                System.out.println();
                System.out.print("Invalid option");
        }

        return foundPerson;
    }

    private Person findPersonById(String purpose) {
        System.out.println("Please enter the person's id to " + purpose);
        x = askIntFromUser();
        return personController.findPersonById(x);
    }

    private List<Person> findPersonByName(String purpose) {
        System.out.println("Please enter the exact first- or lastname to " + purpose + " person");
        String name = askTextFromUser();
        List<Person> personList = personController.findPersonByName(name);
        System.out.println();
        if (personList.isEmpty()) {
            System.out.println("No person has been found");
        } else {
            System.out.println(personList.size() + " person has been found:");
        }
        return personList;
    }

}
