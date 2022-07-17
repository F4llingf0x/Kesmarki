package hu.kesmarki.people.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private static final String NEW_LINE = "\r\n";
    private static final String TAB = "\t";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String country;
    private String county;
    private String city;
    private String postalCode;
    private String streetName;
    private String houseNumber;

    private boolean isDeleted;

    @ManyToOne
    private Person person;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "address", cascade = CascadeType.MERGE)
    private List<Contact> contact;


    @Override
    public String toString() {
        return "id:" + TAB + id + TAB + NEW_LINE +
                TAB + "country:" + TAB + TAB + country + NEW_LINE +
                TAB + "county:" + TAB + TAB + TAB + county + NEW_LINE +
                TAB + "city:" + TAB + TAB + TAB + city + NEW_LINE +
                TAB + "postalCode:" + TAB + TAB + postalCode + NEW_LINE +
                TAB + "streetName:" + TAB + TAB + streetName + NEW_LINE +
                TAB + "houseNumber:" + TAB + houseNumber + NEW_LINE;
    }

}
