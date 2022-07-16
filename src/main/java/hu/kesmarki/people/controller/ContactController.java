package hu.kesmarki.people.controller;

import hu.kesmarki.people.domain.Contact;
import hu.kesmarki.people.domain.ContactType;
import hu.kesmarki.people.service.ContactService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ContactController {

    private static final String NEW_LINE = "\r\n";
    private static final String TAB = "\t";

    private static final String PREVIOUS_TEXT = ", previous value: ";
    public static final String CHOOSE_CONTACT_TYPE = "Please choose from the following contact types:" + NEW_LINE +
            TAB + "1. Phone" + NEW_LINE +
            TAB + "2. Homing pigeon";

    private Scanner scanner = new Scanner(System.in);
    private ContactService contactService;

    int x = 0;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    public void contactBuilder(Contact contact) {

        boolean contactIsPresent = (contact != null);
        ContactType previousContactType = null;
        if (!contactIsPresent) {
            contact = new Contact();
        }

        if (contactIsPresent){
            previousContactType = contact.getContactType();
        }

        System.out.println(CHOOSE_CONTACT_TYPE);
        x = askIntFromUser();
        if (x == 1) {
            contact.setContactType(ContactType.PHONE);
            System.out.println();
            System.out.print("Please enter the phone number");

        } else if (x == 2) {
            contact.setContactType(ContactType.HOMING_PIGEON);
            System.out.println();
            System.out.print("Please enter the homing pigeon's serial number");
        }
        if (contactIsPresent && (previousContactType.equals(contact.getContactType()))) {
            System.out.print(PREVIOUS_TEXT + contact.getNumber());
        }
        System.out.println();
        contact.setNumber(askIntFromUser());
        System.out.println();

        if (!contactIsPresent) {
            System.out.println(contactService.addContact(contact));
        } else {
            System.out.println(modifyContact(contact));
        }

    }

    public Contact modifyContact(Contact contact) {
        return contactService.modifyContact(contact);
    }


    public Contact findContactById(int x) {
        return contactService.findContactById(x);
    }

    public int askIntFromUser() {
        int userInt = scanner.nextInt();
        scanner.nextLine();
        return userInt;
    }

    public String askTextFromUser() {
        return scanner.nextLine();
    }


    public List<Contact> findAllUnassignedContact() {
        return contactService.findAllUnassignedContact();
    }

    public Contact deleteContact(Contact contactToDelete) {
        return contactService.deleteContact(contactToDelete);
    }
}
