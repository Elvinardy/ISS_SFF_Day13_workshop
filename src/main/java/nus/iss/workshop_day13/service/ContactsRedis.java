package nus.iss.workshop_day13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import nus.iss.workshop_day13.model.ContactModel;


@Service
public class ContactsRedis implements ContactsRepo {
    // private final String CONTACT_CACHE = "CONTACT";

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(final ContactModel ctc) {
        redisTemplate.opsForValue().set(ctc.getId() + "_email", ctc.getEmail());
        redisTemplate.opsForValue().set(ctc.getId() + "_name", ctc.getName());
        redisTemplate.opsForValue().set(ctc.getId() + "_phoneNumber", Integer.toString(ctc.getPhoneNumber()));
    }

    @Override
    public ContactModel findById(final String contactId) {
        String email = redisTemplate.opsForValue().get(contactId + "_email");
        String name = redisTemplate.opsForValue().get(contactId + "_name");
        String phoneNumber = redisTemplate.opsForValue().get(contactId + "_phoneNumber");
        ContactModel returnCtc = new ContactModel(name, email, Integer.parseInt(phoneNumber));
        return returnCtc;
    }

  
    // public Map<String, ContactModel> findAll {
    //     return redisTemplate.opsForList().range(CONTACT_CACHE, 0, -1);
    // }
 

    

    

}
