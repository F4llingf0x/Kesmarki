package hu.kesmarki.persons.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private static final String NEW_LINE = "\r\n";
    private static final String TAB = "\t";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String occupation;

    private boolean isDeleted;

    @OneToMany(mappedBy = "person")
    private List<Address> address;

    @Override
    public String toString() {
        return  "id:" + TAB + id + NEW_LINE +
                TAB + "firstName:" + TAB + firstName + NEW_LINE +
                TAB + "lastName:" + TAB + lastName + NEW_LINE +
                TAB + "occupation:" + TAB + occupation + NEW_LINE;
    }
}
