package hu.kesmarki.people.ui;

import hu.kesmarki.people.controller.CommonCommands;
import hu.kesmarki.people.exceptionHandling.Exception;
import org.springframework.stereotype.Component;

@Component
public class MainUI extends CommonCommands {
    private static final String NEW_LINE = "\r\n";
    private static final String TAB = "\t";
    public static String MAIN_MENU_MESSAGE = "Please choose from the following options:" + NEW_LINE +
            TAB + "1. Person actions" + NEW_LINE +
            TAB + "2. Address actions" + NEW_LINE +
            TAB + "3. Contact actions" + NEW_LINE +
            TAB + "4. Exit";

    //TODO welcomeMessage
    //TODO exitMessage

    int x = 0;

    private PersonUI personUI;
    private AddressUI addressUI;
    private ContactUI contactUI;
    private Exception exception;

    public MainUI(PersonUI personUI, AddressUI addressUI, ContactUI contactUI, Exception exception) {
        this.personUI = personUI;
        this.addressUI = addressUI;
        this.contactUI = contactUI;
        this.exception = exception;
    }

    public void init() {

        while (x != -1) {
            System.out.println();
            System.out.println(MAIN_MENU_MESSAGE);
            x = askIntFromUser();
            mainMenu(x);
        }
    }

    public void mainMenu(int value) {
        switch (value) {
            case 1:
                boolean isPersonMenuTerminated = false;
                while (!isPersonMenuTerminated) {
                    System.out.println();
                    System.out.println(PersonUI.PERSON_MENU_MESSAGE);
                    x = askIntFromUser();
                    isPersonMenuTerminated = personUI.personMenu(x);
                }
                break;

            case 2:
                boolean isAddressMenuTerminated = false;
                while (!isAddressMenuTerminated) {
                    System.out.println();
                    System.out.println(AddressUI.ADDRESS_MENU_MESSAGE);
                    x = askIntFromUser();
                    isAddressMenuTerminated = addressUI.addressMenu(x);
                }
                break;

            case 3:
                boolean isContactMenuTerminated = false;
                while (!isContactMenuTerminated) {
                    System.out.println();
                    System.out.println(ContactUI.CONTACT_MENU_MESSAGE);
                    x = askIntFromUser();
                    isContactMenuTerminated = contactUI.contactMenu(x);
                }
                break;

            case 4:
                System.out.println(9);
                x = -1;
                break;
            default:
                System.out.println();
                System.out.print("Invalid option");
        }
    }

}
