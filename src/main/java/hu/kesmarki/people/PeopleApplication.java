package hu.kesmarki.people;

import hu.kesmarki.people.ui.MainUI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PeopleApplication implements CommandLineRunner {


    private MainUI mainUI;

    public PeopleApplication(MainUI mainUI) {
        this.mainUI = mainUI;
    }

    public static void main(String[] args) {
        SpringApplication.run(PeopleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mainUI.init();

        //TODO TESZTEK
        //TODO README

    }
}
