package hu.kesmarki.people.domain;

import com.sun.istack.NotNull;
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

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String occupation;

    private boolean isDeleted;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person", cascade = CascadeType.MERGE)
    private List<Address> address;

    @Override
    public String toString() {
        return "id:" + TAB + id + NEW_LINE +
                TAB + "firstName:" + TAB + firstName + NEW_LINE +
                TAB + "lastName:" + TAB + lastName + NEW_LINE +
                TAB + "occupation:" + TAB + occupation + NEW_LINE
                +NEW_LINE;
    }
}
