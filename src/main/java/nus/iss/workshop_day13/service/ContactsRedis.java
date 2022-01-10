package nus.iss.workshop_day13.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import nus.iss.workshop_day13.model.ContactModel;


@Service
public class ContactsRedis implements ContactRepo {
    private final String CONTACT_CACHE = "CONTACT";

    @Autowired
    RedisTemplate<String, ContactModel> redisTemplate;

    @Override
    public void save(final ContactModel ctc) {
        redisTemplate.opsForValue().set(ctc.getId(),ctc);
    }

    @Override
    public ContactModel findById(final String contactId) {
        return (ContactModel)redisTemplate.opsForValue().get(contactId);
    }

  
    public Map<String, ContactModel> findAll; {
        return redisTemplate.opsForList().range(CONTACT_CACHE, 0, -1);
    }
 

    

    

}
