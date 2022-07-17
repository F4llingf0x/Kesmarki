package hu.kesmarki.people.controller;

import hu.kesmarki.people.exceptionHandling.Exception;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CommonCommands {


    private Scanner scanner = new Scanner(System.in);

    public int askIntFromUser() {
        int userInt = 0;
        try {
            userInt = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            Exception exception = new Exception();
            return exception.badInput();
        }
        return userInt;
    }

    public String askTextFromUser() {
        return scanner.nextLine();
    }

    public String askYNFromUser() {
        String text = scanner.nextLine();
        if (text.equalsIgnoreCase("y") || text.equalsIgnoreCase("n")){
            return text;
        }else{
            return null;
        }
    }

}
