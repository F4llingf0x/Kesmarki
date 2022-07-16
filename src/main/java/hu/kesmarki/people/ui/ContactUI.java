package hu.kesmarki.people.ui;

import hu.kesmarki.people.controller.ContactController;
import hu.kesmarki.people.domain.Address;
import hu.kesmarki.people.domain.Contact;
import hu.kesmarki.people.domain.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ContactUI {

    private static final String NEW_LINE = "\r\n";
    private static final String TAB = "\t";

    public static final String CONTACT_MENU_MESSAGE = "Please choose from the following options:" + NEW_LINE +
            TAB + "1. Add contact" + NEW_LINE +
            TAB + "2. Modify contact" + NEW_LINE +
            TAB + "3. Assign to or change address" + NEW_LINE +
            TAB + "4. Find contact" + NEW_LINE +
            TAB + "5. List all not assigned contacts" + NEW_LINE +
            TAB + "6. Delete contact" + NEW_LINE +
            TAB + "7. Back";

    public static final String FIND_CONTACT_MESSAGE = "Please choose from the following options:" + NEW_LINE +
            TAB + "1. Find contact by contact id" + NEW_LINE +
            TAB + "2. Find contact by person" + NEW_LINE +
            TAB + "3. Find contact by address" + NEW_LINE +
            TAB + "4. Back";

    int x = 0;

    private Scanner scanner = new Scanner(System.in);
    private ContactController contactController;
    private PersonUI personUI;
    private AddressUI addressUI;

    public ContactUI(ContactController contactController, PersonUI personUI, AddressUI addressUI) {
        this.contactController = contactController;
        this.personUI = personUI;
        this.addressUI = addressUI;
    }

    public boolean contactMenu(int value) {
        boolean isTerminated = false;
        Contact foundContact = null;
        switch (value) {
            case 1:
                contactController.contactBuilder(null);
                break;

            case 2:
                foundContact = findContact("modify");
                if (foundContact != null) {
                    contactController.contactBuilder(foundContact);
                } else {
                    System.out.println("Contact has not been found");
                }
                break;

            case 3:
                foundContact = findContactById("assign");
                Address foundAddress = addressUI.findAddress("assign");
                foundContact.setAddress(foundAddress);
                contactController.modifyContact(foundContact);
                break;

            case 4:
                foundContact = findContact("find");
                System.out.println(foundContact);
                break;

            case 5:
                List<Contact> contacts = contactController.findAllUnassignedContact();
                System.out.println(contacts.size() + " contact has been found");
                System.out.println(contacts);
                break;

            case 6:
                Contact contactToDelete = findContact("delete");
                if (contactToDelete != null) {
                    Contact deletedContact = contactController.deleteContact(contactToDelete);
                    System.out.println();
                    System.out.println(deletedContact.getContactType() +
                            " contact with number: " + deletedContact.getNumber() + ", has been deleted");
                }
                break;

            case 7:
                isTerminated = true;
                break;
        }
        return isTerminated;
    }


    public Contact findContact(String purpose) {
        Contact foundContact = null;
        Address foundAddress = null;
        System.out.println(FIND_CONTACT_MESSAGE);
        int x = askIntFromUser();
        switch (x) {
            case 2:
                Person foundPerson = personUI.findPerson("find");
                List<Address> addressList = foundPerson.getAddress();
                System.out.println(addressList);
                if (addressList.size() == 1) {
                    foundAddress = addressList.get(0);
                }
            case 3:
                if (foundAddress == null) {
                    foundAddress = addressUI.findAddressById(purpose);
                }
                List<Contact> contactList = foundAddress.getContact();
                if (contactList.size() == 1) {
                    foundContact = contactList.get(0);
                    break;
                }
                System.out.println(contactList);
            case 1:
                foundContact = findContactById(purpose);
                break;
            case 4:
                break;
        }

        return foundContact;
    }

    public Contact findContactById(String purpose) {
        System.out.println("Please enter the contact's id to " + purpose);
        int x = askIntFromUser();
        return contactController.findContactById(x);
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
