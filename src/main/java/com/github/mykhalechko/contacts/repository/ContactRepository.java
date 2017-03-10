package com.github.mykhalechko.contacts.repository;

import com.github.mykhalechko.contacts.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("select p from Contact p where p.user.id = :user_id")
    List<Contact> findAllUserContacts(@Param("user_id") Long id);

    @Query("select p from Contact p " +
            "where p.user.id = :user_id " +
            "and " +
            "(" +
            "UPPER (p.name) LIKE UPPER(CONCAT('%',:name,'%'))" +
            "or " +
            "UPPER (p.surname) LIKE UPPER(CONCAT('%',:surname,'%'))" +
            "or " +
            "UPPER (p.mobilePhone) LIKE UPPER(CONCAT('%',:mobilePhone,'%'))" +
            "or " +
            "UPPER (p.homePhone) LIKE UPPER(CONCAT('%',:homePhone,'%'))" +
            ")")
    List<Contact> searchContacts(@Param("user_id") Long id,
                                 @Param("name") String name,
                                 @Param("surname") String surname,
                                 @Param("mobilePhone") String mobilePhone,
                                 @Param("homePhone") String homePhone);
}
