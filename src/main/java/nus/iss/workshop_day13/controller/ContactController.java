package nus.iss.workshop_day13.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import nus.iss.workshop_day13.util.Contacts;

@Controller
public class ContactController {
    private Logger logger = LoggerFactory.getLogger(ContactController.class);

    @PostMapping("/contact")
    public String showContactForm (@ModelAttribute Contacts contact, Model model) {

        model.addAttribute("name", contact);
        model.addAttribute("email", contact);
        model.addAttribute("phoneNumber", contact);

        return "contact";


    }
}

