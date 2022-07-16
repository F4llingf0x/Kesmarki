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
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String country;
    private String state;
    private String postalCode;
    private String street;
    private String number;

    private boolean isDeleted;

    @ManyToOne
    private Person person;

    @OneToMany(mappedBy = "address")
    private List<Contact> contact;

}
