package com.github.mykhalechko.contacts.service;

import com.github.mykhalechko.contacts.entity.Contact;
import com.github.mykhalechko.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> findAll() {
        List<Contact> contacts = contactRepository.findAll();
        sortContacts(contacts);
        return contacts;
    }

    private void sortContacts(List<Contact> contacts) {
        // TODO: 08.03.2017 use java8
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact p1, Contact p2) {
                String ContactName1 = p1.getName().toUpperCase();
                String ContactName2 = p2.getName().toUpperCase();

                //ascending order
                return ContactName1.compareTo(ContactName2);

                //descending order
                //return ContactName2.compareTo(ContactName1);
            }
        });
    }

    @Override
    public List<Contact> findAllUserContacts(Long id) {
        List<Contact> contacts = contactRepository.findAllUserContacts(id);
        sortContacts(contacts);
        return contacts;
    }

    @Override
    public Contact findById(Long id) {
        return contactRepository.findOne(id);
    }

    @Override
    public Contact create(Contact contact) {
        return contactRepository.saveAndFlush(contact);
    }

    @Override
    public Contact edit(Contact contact) {
        return contactRepository.saveAndFlush(contact);
    }

    @Override
    public void deleteById(Long id) {
        contactRepository.delete(id);
    }
}
