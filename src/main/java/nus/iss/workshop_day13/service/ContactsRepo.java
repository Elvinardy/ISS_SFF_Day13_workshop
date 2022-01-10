package nus.iss.workshop_day13.service;

import nus.iss.workshop_day13.model.ContactModel;

public interface ContactsRepo {
    
    public void save(final ContactModel ctc);
    public ContactModel findById(final String contactId);
}
