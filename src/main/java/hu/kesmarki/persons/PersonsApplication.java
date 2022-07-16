package hu.kesmarki.persons;

import hu.kesmarki.persons.ui.MainUI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonsApplication implements CommandLineRunner {


    private MainUI mainUI;

    public PersonsApplication(MainUI mainUI) {
        this.mainUI = mainUI;
    }

    public static void main(String[] args) {
        SpringApplication.run(PersonsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mainUI.init();

//            String str1 = "\u2713";
//            byte[] charset = str1.getBytes("UTF-8");
//            String newstr = new String(charset, "UTF-8");
//            System.out.println(newstr);


    }
}
