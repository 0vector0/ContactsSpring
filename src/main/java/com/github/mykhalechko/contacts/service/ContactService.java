package com.github.mykhalechko.contacts.service;


import com.github.mykhalechko.contacts.entity.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> findAll();

    List<Contact> findAllUserContacts(Long id);

    Contact findById(Long id);

    Contact create(Contact contact);

    Contact edit(Contact contact);

    void deleteById(Long id);

}


