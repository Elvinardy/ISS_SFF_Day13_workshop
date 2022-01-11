package nus.iss.workshop_day13.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import nus.iss.workshop_day13.model.ContactModel;

@Repository     // for storing and querying data
public interface ContactsRepo {

    
    public void save(final ContactModel ctc);
    public ContactModel findById(final String contactId);
    public List<ContactModel> findAll(int startIndex);
}
