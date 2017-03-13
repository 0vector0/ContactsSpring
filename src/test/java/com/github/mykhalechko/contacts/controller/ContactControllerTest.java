package com.github.mykhalechko.contacts.controller;

import com.github.mykhalechko.contacts.entity.Contact;
import com.github.mykhalechko.contacts.entity.User;
import com.github.mykhalechko.contacts.repository.ContactRepository;
import com.github.mykhalechko.contacts.service.ContactService;
import com.github.mykhalechko.contacts.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {

    @Mock
    Contact contact;
    @Mock
    private ContactService contactService;
    @Mock
    private UserService userService;
    @InjectMocks
    private ContactController controller;
    @Mock
    private View mockView;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(controller)
                .setSingleView(mockView)
                .build();
    }

    @Test
    @WithMockUser
    public void listContacts() throws Exception {

        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact());
        contacts.add(new Contact());

        when(userService.getAuthenticationUser()).thenReturn(new User());
        when(contactService.findAllUserContacts(anyLong())).thenReturn(contacts);

        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("listContacts", hasSize(2)))
                .andExpect(model().attribute("searchString", ""))
                .andExpect(view().name("contacts"))
                .andDo(print());
    }


    @Test
    @WithMockUser
    public void contactDataTest() throws Exception {
        when(contactService.findById(anyLong())).thenReturn(new Contact());
        mockMvc.perform(get("/contacts/dataContact/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("dataContact"))
                .andExpect(model().attribute("contact", instanceOf(Contact.class)))
                .andDo(print());
    }

    @Test
    @WithMockUser
    public void searchContactTest() throws Exception {

        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact());
        contacts.add(new Contact());

        when(userService.getAuthenticationUser()).thenReturn(new User());
        when(contactService.searchContacts(anyLong(), anyString(), anyString(), anyString(), anyString())).thenReturn(contacts);

        mockMvc.perform(
                post("/contacts")
                        .param("searchString", "searchString"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("listContacts", hasSize(2)))
                .andExpect(model().attribute("searchString", ""))
                .andExpect(view().name("contacts"))
                .andDo(print());
    }


    @Test
    @WithMockUser
    public void addContactTest() throws Exception {
        mockMvc.perform(get("/contacts/addContact"))
                .andExpect(status().isOk())
                .andExpect(view().name("addContact"));
    }

    @Test
    @WithMockUser
    public void addContactFailure() throws Exception {

        verifyZeroInteractions(contactService);

        String surname = "surname";
        String name = "name";
        String patronymic = "patronymic";
        String mobilePhone = "mobilePhone";
        String homePhone = "homePhone";
        String address = "address";
        String email = "email";

        this.mockMvc.perform(
                post("/contacts/addContact")
                        .param("surname", surname)
                        .param("name", name)
                        .param("patronymic", patronymic)
                        .param("mobilePhone", mobilePhone))
                .andExpect(model().hasErrors())
                .andExpect(view().name("addContact"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser
    public void addContactSuccess() throws Exception {

        Long id = 1L;
        String surname = "surname";
        String name = "name";
        String patronymic = "patronymic";
        String mobilePhone = "123456789101";
        String homePhone = "123456789101";
        String address = "address";
        String email = "email@email.com";

        Contact returnContact = new Contact(id, surname, name, patronymic, mobilePhone, homePhone, address, email, new User());

        when(contactService.create(anyObject())).thenReturn(returnContact);

        this.mockMvc.perform(
                post("/contacts/addContact")
                        .param("id", id.toString())
                        .param("surname", surname)
                        .param("name", name)
                        .param("patronymic", patronymic)
                        .param("mobilePhone", mobilePhone)
                        .param("homePhone", homePhone)
                        .param("address", address)
                        .param("email", email))
                .andExpect(model().attribute("contact", instanceOf(Contact.class)))
                .andExpect(model().attribute("contact", hasProperty("surname", is(surname))))
                .andExpect(model().attribute("contact", hasProperty("name", is(name))))
                .andExpect(model().attribute("contact", hasProperty("patronymic", is(patronymic))))
                .andExpect(model().attribute("contact", hasProperty("mobilePhone", is(mobilePhone))))
                .andExpect(model().attribute("contact", hasProperty("homePhone", is(homePhone))))
                .andExpect(model().attribute("contact", hasProperty("address", is(address))))
                .andExpect(model().attribute("contact", hasProperty("email", is(email))))
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("redirect:/contacts"))
                .andExpect(status().isOk())
                .andDo(print());

        ArgumentCaptor<Contact> boundContact = ArgumentCaptor.forClass(Contact.class);
        verify(contactService).create(boundContact.capture());

        assertEquals(id, (Long) boundContact.getValue().getId());
        assertEquals(surname, boundContact.getValue().getSurname());
        assertEquals(name, boundContact.getValue().getName());
        assertEquals(patronymic, boundContact.getValue().getPatronymic());
        assertEquals(mobilePhone, boundContact.getValue().getMobilePhone());
        assertEquals(homePhone, boundContact.getValue().getHomePhone());
        assertEquals(address, boundContact.getValue().getAddress());
        assertEquals(email, boundContact.getValue().getEmail());

    }

    @Test
    @WithMockUser
    public void removeContactTest() throws Exception {

        Long id = 1L;

        mockMvc.perform(
                get("/contacts/deleteContact/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/contacts"))
                .andDo(print());

        verify(contactService, times(1)).deleteById(id);
    }
}