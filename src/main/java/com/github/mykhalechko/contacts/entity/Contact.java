package com.github.mykhalechko.contacts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

//Lombok annotations getters, setters, toString and other methods
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "contacts")
public class Contact implements Serializable {

    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "surname")
    @NotEmpty(message = "{contact.error.surnameRequired}")
    @Size(min = 4, message = "{contact.error.surnameLength}")
    private String surname;
    @Column(name = "name")
    @NotEmpty(message = "{contact.error.nameRequired}")
    @Size(min = 4, message = "{contact.error.nameLength}")
    private String name;
    @Column(name = "patronymic")
    @NotEmpty(message = "{contact.error.patronymicRequired}")
    @Size(min = 4, message = "{contact.error.patronymicLength}")
    private String patronymic;

    @Column(name = "mobilePhone")
    @NotEmpty(message = "{contact.error.mobilePhoneRequired}")
//    ^ - begin string
//    [0-9] - numbers
//    {12}  - size
//    $ - end string
//    | - or
//    ^$- empty string
    @Pattern(regexp = "^[0-9]{12}$|^$", message = "{contact.error.mobilePhoneRegexp}")
//    @Size(min = 12, max = 12, message = "{contact.error.mobilePhoneLength}")
    private String mobilePhone;

    @Column(name = "homePhone")
    @Pattern(regexp = "^[0-9]{12}$|^$", message = "{contact.error.homePhoneRegexp}")
//    @Size(min = 12, max = 12, message = "{contact.error.homePhoneLength}")
    private String homePhone;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    @Email(message = "{contact.error.email}")
    private String email;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
