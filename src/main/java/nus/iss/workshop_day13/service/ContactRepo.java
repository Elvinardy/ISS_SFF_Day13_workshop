package nus.iss.workshop_day13.service;

import nus.iss.workshop_day13.model.ContactModel;

public interface ContactRepo {
    
    public void save(final ContactModel ctc);
    public void findBy(final String contactId);
}
