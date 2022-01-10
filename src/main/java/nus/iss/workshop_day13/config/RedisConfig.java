package nus.iss.workshop_day13.config;


import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import nus.iss.workshop_day13.model.ContactModel;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    
    @Bean
    public RedisTemplate<String, ContactModel> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, ContactModel> template = new RedisTemplate();
        template.setConnectionFactory(connectionFactory);

        return template;

    }

}
