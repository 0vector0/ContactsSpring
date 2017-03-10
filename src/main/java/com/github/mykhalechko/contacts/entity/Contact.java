package com.github.mykhalechko.contacts.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

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

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", user=" + user +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
