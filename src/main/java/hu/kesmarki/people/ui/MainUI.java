package hu.kesmarki.people.ui;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainUI {
    private static final String NEW_LINE = "\r\n";
    private static final String TAB = "\t";
    public String mainMenuMessage = "Please choose from the following options:" + NEW_LINE +
            TAB + "1. Person actions" + NEW_LINE +
            TAB + "2. Address actions" + NEW_LINE +
            TAB + "3. Contact actions" + NEW_LINE +
            TAB + "4. Exit";

    int x = 0;

    private PersonUI personUI;
    private AddressUI addressUI;
    private ContactUI contactUI;
    private Scanner scanner = new Scanner(System.in);

    public MainUI(PersonUI personUI, AddressUI addressUI, ContactUI contactUI) {
        this.personUI = personUI;
        this.addressUI = addressUI;
        this.contactUI = contactUI;
    }

    public int askIntFromUser() {
        int userInt = scanner.nextInt();
        scanner.nextLine();
        return userInt;
    }

    public String askTextFromUser() {
        return scanner.nextLine();
    }

    public void init() {

        while (x != -1) {
            System.out.println();
            System.out.println(mainMenuMessage);
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
                    System.out.println(personUI.PERSON_MENU_MESSAGE);
                    x = askIntFromUser();
                    isPersonMenuTerminated = personUI.personMenu(x);
                }
                break;

            case 2:
                boolean isAddressMenuTerminated = false;
                while (!isAddressMenuTerminated) {
                    System.out.println();
                    System.out.println(addressUI.ADDRESS_MENU_MESSAGE);
                    x = askIntFromUser();
                    isAddressMenuTerminated = addressUI.addressMenu(x);
                }
                break;

            case 3:
                boolean isContactMenuTerminated = false;
                while (!isContactMenuTerminated) {
                    System.out.println();
                    System.out.println(contactUI.CONTACT_MENU_MESSAGE);
                    x = askIntFromUser();
                    isContactMenuTerminated = contactUI.contactMenu(x);
                }
                break;

            case 4:
                System.out.println(9);
                x = -1;
                break;
        }
    }

}
