package com.github.mykhalechko.contacts.controller;

import com.github.mykhalechko.contacts.entity.User;
import com.github.mykhalechko.contacts.service.SecurityService;
import com.github.mykhalechko.contacts.service.UserService;
import com.github.mykhalechko.contacts.validator.UserValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

    @Mock
    UserValidator userValidator;
    @Mock
    private UserService userService;
    @Mock
    private SecurityService securityService;
    @InjectMocks
    private RegistrationController controller;
    @Mock
    private View mockView;
    private MockMvc mockMvc;

    private String username;
    private String password;
    private String passwordConfirm;
    private String fullName;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(controller)
                .setSingleView(mockView)
                .build();
    }

    @Test
    public void getRegistration() throws Exception {

        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("registrationForm", instanceOf(User.class)))
                .andExpect(model().attribute("registrationForm", hasProperty("username")))
                .andExpect(model().attribute("registrationForm", hasProperty("password")))
                .andExpect(model().attribute("registrationForm", hasProperty("fullName")))
                .andExpect(view().name("registration"));
    }

    @Test
    public void postRegistrationFailure() throws Exception {

        username = "123456789";
        password = "2";
        passwordConfirm = "password";
        fullName = "fullName";

        this.mockMvc.perform(
                post("/registration")
                        .param("username", username)
                        .param("password", password)
                        .param("passwordConfirm", passwordConfirm)
                        .param("fullName", fullName))
                .andExpect(model().attributeHasErrors("registrationForm"))
                .andExpect(view().name("registration"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void postRegistrationSuccess() throws Exception {

        username = "username";
        password = "password";
        passwordConfirm = "password";
        fullName = "fullName";

        this.mockMvc.perform(
                post("/registration")
                        .param("username", username)
                        .param("password", password)
                        .param("passwordConfirm", passwordConfirm)
                        .param("fullName", fullName))
                .andExpect(model().attribute("registrationForm", hasProperty("username", is(username))))
                .andExpect(model().attribute("registrationForm", hasProperty("password", is(password))))
                .andExpect(model().attribute("registrationForm", hasProperty("passwordConfirm", is(passwordConfirm))))
                .andExpect(model().attribute("registrationForm", hasProperty("fullName", is(fullName))))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attribute("username", "name"))
                .andExpect(view().name("redirect:/contacts"))
                .andExpect(status().isOk())
                .andDo(print());

        verify(userService).save(anyObject());
        verify(securityService).autologin(username, password);
    }

}