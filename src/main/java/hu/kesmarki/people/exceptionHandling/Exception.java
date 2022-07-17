package hu.kesmarki.people.exceptionHandling;

import hu.kesmarki.people.controller.CommonCommands;
import org.springframework.stereotype.Component;

@Component
public class Exception extends CommonCommands {

    public int badInput() {
        System.out.println();
        System.out.println("Invalid entry type, please revise");
        return askIntFromUser();
    }

}
