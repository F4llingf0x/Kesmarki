package hu.kesmarki.persons.ui;

import hu.kesmarki.persons.controller.AddressController;
import hu.kesmarki.persons.domain.Address;
import hu.kesmarki.persons.domain.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class AddressUI {

    private Scanner scanner = new Scanner(System.in);
    private AddressController addressController;
    private PersonUI personUI;

    private static final String NEW_LINE = "\r\n";
    private static final String TAB = "\t";

    public static final String ADDRESS_MENU_MESSAGE = "Please choose from the following options:" + NEW_LINE +
            TAB + "1. Add address" + NEW_LINE +
            TAB + "2. Modify address" + NEW_LINE +
            TAB + "3. Assign to or change person" + NEW_LINE +
            TAB + "4. List all addresses of person" + NEW_LINE +
            TAB + "6. List all not assigned addresses" + NEW_LINE +
            TAB + "7. Delete address" + NEW_LINE +
            TAB + "8. Back";

    public static final String FIND_ADDRESS_MESSAGE = "Please choose from the following options:" + NEW_LINE +
            TAB + "1. Find address by address id" + NEW_LINE +
            TAB + "2. Find address by person" + NEW_LINE +
            TAB + "3. Back";

    public AddressUI(AddressController addressController, PersonUI personUI) {
        this.addressController = addressController;
        this.personUI = personUI;
    }


    public boolean addressMenu(int value) {
        boolean isTerminated = false;
        switch (value) {
            case 1:
                addressController.addressBuilder(null);
                break;

            case 2:
                Address foundAddress;
                if (foundAddress != null) {
                    addressController.addressBuilder(foundAddress);
                }else {
                    System.out.println("Person has not been found");
                }
                break;

            case 3:
                System.out.println(findAddressById("find"));
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

    public Address findAddress(String purpose) {
        Address foundAddress = null;
        System.out.println(FIND_ADDRESS_MESSAGE);
        int x = askIntFromUser();
        switch (x) {
            case 2:
                Person foundPerson = personUI.findPerson("find");
                List<Address> addressList = foundPerson.getAddress();
                System.out.println(addressList);
                if (addressList.size() == 1) {
                    foundAddress = addressList.get(0);
                    break;
                }
            case 1:
                foundAddress = findAddressById(purpose);
                break;
            case 3:
                break;
        }

        return foundAddress;
    }



    private Address findAddressById(String purpose) {
        System.out.println("Please enter the address' id to " + purpose);
        int x = askIntFromUser();
        Address foundAddress = addressController.findAddressById(x);
        return foundAddress;
    }

    public int askIntFromUser() {
        int userInt = scanner.nextInt();
        scanner.nextLine();
        return userInt;
    }

    public String askTextFromUser() {
        return scanner.nextLine();
    }

}
