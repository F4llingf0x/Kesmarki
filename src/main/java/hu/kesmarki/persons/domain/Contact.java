package hu.kesmarki.persons.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private Integer phoneNumber;
    private String linkedinProfile;

    private boolean isDeleted;

    @ManyToOne
    private Address address;


}
