package com.github.mykhalechko.contacts.repository;

import com.github.mykhalechko.contacts.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("select p from Contact p where p.user.id = :user_id")
    List<Contact> findAllUserContacts(@Param("user_id") Long id);
}
