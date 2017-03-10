package com.github.mykhalechko.contacts.controller;


import com.github.mykhalechko.contacts.entity.Contact;
import com.github.mykhalechko.contacts.repository.ContactRepository;
import com.github.mykhalechko.contacts.service.ContactService;
import com.github.mykhalechko.contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String listContacts(Model model) {
        List<Contact> contacts = this.contactService.findAllUserContacts(userService.getAuthenticationUser().getId());
        model.addAttribute("listContacts", contacts);
        model.addAttribute("searchString", "");
        return "contacts";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String searchContact(@ModelAttribute("searchString") String searchString, Model model) {

        List<Contact> contacts = this.contactService.searchContacts(
                userService.getAuthenticationUser().getId(), searchString, searchString, searchString, searchString);
        model.addAttribute("listContacts", contacts);
        model.addAttribute("searchString", "");

        return "contacts";
    }

    @RequestMapping(value = "/addContact", method = RequestMethod.GET)
    public String addContact(Model model) {
        model.addAttribute("contact", new Contact());

        return "addContact";
    }


    @RequestMapping(value = "/addContact", method = RequestMethod.POST)
    public String addContact(@Valid Contact contact, BindingResult bindingResult) {

        System.out.println(contact);

        if (bindingResult.hasErrors()) {
            System.out.printf("error");
            return "addContact";
        }

        contact.setUser(userService.getAuthenticationUser());
        this.contactService.create(contact);
        return "redirect:/contacts";
    }

    @RequestMapping("/deleteContact/{id}")
    public String removeContact(@PathVariable("id") long id) {
        this.contactService.deleteById(id);

        return "redirect:/contacts";
    }

    @RequestMapping(value = "/editContact/{id}")
    public String editContact(@PathVariable("id") long id, Model model) {
        model.addAttribute("contact", this.contactService.findById(id));
        return "editContact";
    }


    @RequestMapping(value = "/editContact", method = RequestMethod.POST)
    public String editContact(@ModelAttribute("contact") Contact contact) {

        this.contactService.edit(contact);
        return "redirect:/contacts";
    }

    @RequestMapping("/dataContact/{id}")
    public String contactData(@PathVariable("id") long id, Model model) {
        model.addAttribute("contact", this.contactService.findById(id));

        return "dataContact";
    }


}
