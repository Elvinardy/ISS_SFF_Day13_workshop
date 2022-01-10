package nus.iss.workshop_day13.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import nus.iss.workshop_day13.model.ContactModel;
import nus.iss.workshop_day13.service.ContactsRedis;

@Controller
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
     // by declaring Autowired, it eliminates the need for getters and setters.
   
    
    @Autowired
    ContactsRedis service;

    @GetMapping("/")
    public String contactForm(Model model) {
        model.addAttribute("contact", new ContactModel());
        return "contact";
    }

    @GetMapping("/getContact/{contactID}") 
        public String getContact(Model model, @PathVariable(value="contactId") String contactId) {
            logger.info(contactId);
            //Contacts ct = new Contacts();
            ContactModel ctc = service.findById(contactId);
            model.addAttribute("contact", ctc);
           
            return "created";        
        }
    

    @PostMapping("/contact")
    public String submitForm (@ModelAttribute ContactModel contact, Model model) {
        logger.info("Name: " + contact.getName());
        logger.info("Email: " + contact.getEmail());
        logger.info("Phone Number: " + contact.getPhoneNumber());
        //Contacts ct = new Contacts();
        //ct.saveContact(contact, model, appargs);  // save contacts info and writes into a File by calling the savaContact function
        ContactModel persistToRedisCtc = new ContactModel(
                    contact.getName(),
                    contact.getEmail(),
                    contact.getPhoneNumber()
        );
        service.save(persistToRedisCtc);
        model.addAttribute("contact", persistToRedisCtc);

        return "created";
    }
}

