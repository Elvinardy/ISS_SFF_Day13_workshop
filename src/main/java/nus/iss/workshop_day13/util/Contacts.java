package nus.iss.workshop_day13.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.ui.Model;

import nus.iss.workshop_day13.model.ContactModel;

//  this is a helper class with all the methods needed are declared here
public class Contacts {
    private static final Logger logger = LoggerFactory.getLogger(Contacts.class);

    public void saveContact(ContactModel contact, Model model, ApplicationArguments appArgs) {
        String dataFilename = contact.getId();
        Set<String> optNames = appArgs.getOptionNames();  // getOptionNames Return the names of all option arguments.
        String[] optNamesArr = optNames.toArray(new String[optNames.size()]);
        List<String> optValues = appArgs.getOptionValues(optNamesArr[0]);  // getOptionvalues Return the collection of values associated with the arguments option having the given name.
        String[] optValuesArr = optValues.toArray(new String[optValues.size()]);
        PrintWriter printWriter = null;

    try {
        FileWriter fileWriter = new FileWriter(optValuesArr[0] + "/" + dataFilename, Charset.forName("utf-8"));  // utf-8 is the most common character encoding method used on the internet, defautlt character set for html5
            // this charset.forname function passes a canonical name or an alias and its respective charset name is returned.
          printWriter = new PrintWriter(fileWriter);
          printWriter.println(contact.getName());
          printWriter.println(contact.getEmail());
          printWriter.println(contact.getPhoneNumber());

    } catch (IOException e) {
        logger.error(e.getMessage());     // use logger.error in springboot applications
    } finally {
        printWriter.close();       // use finally so that this action will always take place at the end
    }

    model.addAttribute("contact", new ContactModel(contact.getName(), 
                                contact.getEmail(), 
                                contact.getPhoneNumber()));     // Add these 3 attributes to a new model / object 
                                                                // new model object is created fot isolation, to not share the data in the form
}

    public void getContactById(Model model, String contactId, ApplicationArguments appArgs) {
        Set<String> optNames = appArgs.getOptionNames();
        String[] optNamesArr = optNames.toArray(new String[optNames.size()]);
        List<String> optValues = appArgs.getOptionValues(optNamesArr[0]);
        String[] optValuesArr = optValues.toArray(new String[optValues.size()]);
        ContactModel idCheck = new ContactModel();

        try {
            Path filePath = new File(optValuesArr[0]+ "/" + contactId).toPath();  // .toPath method returns a Path constructed from this abstract path.
            Charset cs = Charset.forName("utf-8");
            List<String> stringList = Files.readAllLines(filePath, cs);  // returns the lines from the file as a List
            // Read all lines from a file. This method ensures that the file is closed when all bytes have been read or an I/O error, 
            //  or other runtime exception, is thrown. Bytes from the file are decoded into characters using the specified charset.
            idCheck.setName(stringList.get(0));
            idCheck.setEmail(stringList.get(1));
            idCheck.setPhoneNumber(Integer.parseInt(stringList.get(2)));
            // Display the contents of the file in a HTML document
        } catch(IOException e) {
            logger.error("Error detected" + e.getMessage());
        }
        model.addAttribute("contact", idCheck);

        }
    }



