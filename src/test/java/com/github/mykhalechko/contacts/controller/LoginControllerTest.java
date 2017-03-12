package com.github.mykhalechko.contacts.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class LoginControllerTest {

    @InjectMocks
    private LoginController controller;

    @Mock
    private View mockView;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(controller)
                .setSingleView(mockView)
                .build();
    }

    @Test
//    @WithMockUser
    public void getLogin() throws Exception {

//        without parameters
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("error", false))
                .andExpect(model().attribute("logout", false))
                .andExpect(view().name("login"));

//        with "error" parameter
        mockMvc.perform(get("/login")
                .param("error", "error"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("error", true))
                .andExpect(model().attribute("logout", false))
                .andExpect(view().name("login"));

//        with "error" parameter
        mockMvc.perform(get("/login")
                .param("logout", "logout"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("error", false))
                .andExpect(model().attribute("logout", true))
                .andExpect(view().name("login"));
    }


}