package com.github.mykhalechko.contacts.entity;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @Size(min = 4)
    private String surname;

    @Column(name = "name")
    @NotNull
    @Size(min = 4)
    private String name;

    @Column(name = "patronymic")
    @NotNull
    @Size(min = 4)
    private String patronymic;

    // TODO: 07.03.2017 check phone
    @Column(name = "mobilePhone")
    @NotNull
    @Size(min = 12)
    private String mobilePhone;

    // TODO: 07.03.2017 check phone
    @Column(name = "homePhone")
    @Size(min = 12)
    private String homePhone;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    @Email
    private String email;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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
