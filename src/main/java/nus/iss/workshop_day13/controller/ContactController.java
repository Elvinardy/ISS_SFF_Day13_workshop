package nus.iss.workshop_day13.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import nus.iss.workshop_day13.model.ContactModel;
import nus.iss.workshop_day13.util.Contacts;

@Controller
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired  // by declaring Autowired, it eliminates the need for getters and setters.
    private ApplicationArguments appargs; 

    @GetMapping("/")
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contacts());
        return "contact";
    }

    @GetMapping("/getContact/{contactID}") 
        public String getContact(Model model, @PathVariable(value="contactId") String contactId) {
            logger.info(contactId);
            Contacts ct = new Contacts();
            ct.getContactById(model, contactId, appargs);
            return "showcontact";        
        }
    

    @PostMapping("/contact")
    public String submitForm (@ModelAttribute ContactModel contact, Model model) {
        logger.info("Name: " + contact.getName());
        logger.info("Email: " + contact.getEmail());
        logger.info("Phone Number: " + contact.getPhoneNumber());
        Contacts ct = new Contacts();
        ct.saveContact(contact, model, appargs);

        return "showcontact";
    }
}

