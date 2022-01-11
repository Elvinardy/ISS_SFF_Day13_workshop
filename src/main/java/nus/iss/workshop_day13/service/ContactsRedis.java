package nus.iss.workshop_day13.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import nus.iss.workshop_day13.model.ContactModel;


@Service    // Holds business logic / methods
public class ContactsRedis implements ContactsRepo {
    // private final String CONTACT_CACHE = "CONTACT";
    private static final Logger logger = LoggerFactory.getLogger(ContactsRedis.class);
    private static final String CONTACT_ENTITY = "contactlist";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;        // Inject an instance of RedisTemplate into service object

    @Override
    public void save(final ContactModel ctc) {
        redisTemplate.opsForList().leftPush(CONTACT_ENTITY, ctc.getId());       // CONTACT_ENTITY(Key) , ctc.getId() (Value)
        redisTemplate.opsForHash().put(CONTACT_ENTITY + "_Map", ctc.getId(), ctc);

        // redisTemplate.opsForValue().set(ctc.getId() + "_email", ctc.getEmail());
        // redisTemplate.opsForValue().set(ctc.getId() + "_name", ctc.getName());
        // redisTemplate.opsForValue().set(ctc.getId() + "_phoneNumber", Integer.toString(ctc.getPhoneNumber()));
    }

    @Override
    public ContactModel findById(final String contactId) {
       ContactModel result = (ContactModel)redisTemplate.opsForHash()
                        .get(CONTACT_ENTITY + "_Map", contactId);
       logger.info(" >>> " + result.getEmail());
       return result;
       
    }

    @Override
    public List<ContactModel> findAll(int startIndex) {
        List<Object> fromContactList = redisTemplate.opsForList()
                            .range(CONTACT_ENTITY, startIndex, startIndex + 9);     // creating a Sublist

        List<ContactModel> ctcs = redisTemplate.opsForHash()
                        .multiGet(CONTACT_ENTITY + "_Map", fromContactList)     // putting the sublist into a new list
                        .stream()
                        .filter(ContactModel.class::isInstance)
                        .map(ContactModel.class::cast)
                        .toList();

                return ctcs;
        
           
           /*  List<Object> results = (List<Object>redisTemplate.opsForHash())
                    .values(CONTACT_ENTITY + "_Map");
                    return results; */
        }
    }
  
    // public Map<String, ContactModel> findAll {
    //     return redisTemplate.opsForList().range(CONTACT_CACHE, 0, -1);
    // }

