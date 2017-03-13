package com.github.mykhalechko.contacts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutController {

    @RequestMapping(value = "/about")
    public ModelAndView getAbout() {
        return new ModelAndView("about");
    }

}
