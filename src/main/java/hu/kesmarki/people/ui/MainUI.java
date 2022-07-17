package hu.kesmarki.people.ui;

import hu.kesmarki.people.controller.CommonCommands;
import hu.kesmarki.people.exceptionHandling.Exception;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class MainUI extends CommonCommands {
    private static final String NEW_LINE = "\r\n";
    private static final String TAB = "\t";
    private static final String WELCOME_MESSAGE  = "Welcome to the person handling application";
    public static String MAIN_MENU_MESSAGE = "Please choose from the following options:" + NEW_LINE +
            TAB + "1. Person actions" + NEW_LINE +
            TAB + "2. Address actions" + NEW_LINE +
            TAB + "3. Contact actions" + NEW_LINE +
            TAB + "4. Exit";

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
        System.out.println();
        System.out.println(TAB + WELCOME_MESSAGE);
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
                System.out.println();
                exitMessage();
                x = -1;
                break;
            default:
                System.out.println();
                System.out.print("Invalid option");
        }


    }

    private void exitMessage() {
        try {
            String str1 = "\u2122";
            byte[] charset = str1.getBytes("UTF-8");
            String newstr = new String(charset, "UTF-8");

            System.out.print("Thank you for choosing Késmárki");
            System.out.print(newstr);
            System.out.println(" softwares");
            System.out.println();

        } catch (UnsupportedEncodingException e) {
            System.out.print(" TM");
        }

    }

}
