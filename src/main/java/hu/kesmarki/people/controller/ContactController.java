package hu.kesmarki.people.controller;

import hu.kesmarki.people.domain.Contact;
import hu.kesmarki.people.domain.ContactType;
import hu.kesmarki.people.service.ContactService;
import org.springframework.stereotype.Component;

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
        if (!contactIsPresent) {
            contact = new Contact();
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
        if (contactIsPresent) {
            System.out.print(PREVIOUS_TEXT + contact.getNumber());
        }
        contact.setNumber(askIntFromUser());
        System.out.println();

        if (!contactIsPresent) {
            System.out.println(contactService.addContact(contact));
        } else {
            System.out.println(modifyContact(contact));
        }

    }

    private Contact modifyContact(Contact contact) {
        contactService.modifyContact(contact);
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
