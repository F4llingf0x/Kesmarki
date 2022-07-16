package hu.kesmarki.people.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    private static final String NEW_LINE = "\r\n";
    private static final String TAB = "\t";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ContactType contactType;

    @NotNull
    private Integer number;

    private boolean isDeleted;

    @ManyToOne
    private Address address;

    @Override
    public String toString() {
        return "id:" + TAB + id + NEW_LINE +
                TAB + "contact type:" + TAB + contactType + NEW_LINE +
                TAB + "number:" + TAB + TAB + TAB + number + NEW_LINE;
    }


}
