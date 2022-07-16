package hu.kesmarki.persons.ui;

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
    private Scanner scanner = new Scanner(System.in);

    public MainUI(PersonUI personUI) {
        this.personUI = personUI;
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
                boolean isTerminated = false;
                while (!isTerminated) {
                    System.out.println();
                    System.out.println(personUI.PERSON_MENU_MESSAGE);
                    x = askIntFromUser();
                    isTerminated = personUI.personMenu(x);
                }
                break;

            case 2:
                System.out.println("Address actions");
                break;

            case 3:
                System.out.println("Contact actions");
                break;

            case 4:
                System.out.println(9);
                x = -1;
                break;
        }
    }

}
