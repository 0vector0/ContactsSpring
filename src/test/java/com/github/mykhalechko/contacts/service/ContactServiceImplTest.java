package com.github.mykhalechko.contacts.service;

import com.github.mykhalechko.contacts.entity.Contact;
import com.github.mykhalechko.contacts.repository.ContactRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactServiceImpl contactService;

    Contact contact = new Contact();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

    @Test
    public void findAllUserContacts() throws Exception {

    }

    @Test
    public void searchContacts() throws Exception {

    }

    @Test
    public void findById() throws Exception {

        Long id = 1L;
        Contact contact = new Contact();
        contact.setId(id);
        contact.setSurname("testSurname");
        contact.setName("testName");

        when(contactRepository.findOne(id)).thenReturn(contact);

        Contact boundContact = contactRepository.findOne(id);

        Assert.assertEquals(contact, boundContact);
    }

    @Test
    public void create() throws Exception {

    }

    @Test
    public void edit() throws Exception {

    }

    @Test
    public void deleteById() throws Exception {

    }

}