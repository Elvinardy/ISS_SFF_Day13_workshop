package nus.iss.workshop_day13.config;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import nus.iss.workshop_day13.controller.ContactController;

@Configuration  // holds configuration (factory methods) to create and configure beans / objects
//@EnableConfigurationProperties(RedisProperties.class)  //disable to override the configuration

public class RedisConfig {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
    
    @Value("${spring.redis.host}")      // values are injected from resources/application.properties
    private String redisHost;
    @Value("${spring.redis.port}") 
    private Optional<Integer> redisPort;        // indicates value is optional
    @Value("${spring.redis.password}") 
    private String redisPassword;
    
    @Bean  //  Factory methods are annotated with a bean
    @Scope("singleton")     // Only a single instance is created and shared for a singleton
    public RedisTemplate<String, Object> redisTemplate() {      // requires a default constructor or a constructor without any parameters
       
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();     // configuring the database
        logger.info("redis host port> " + redisHost + " " + redisPort + " " + redisPassword);
        // config.setDatabase(redisDatabase);
        config.setHostName(redisHost);      // Confiq values are injected from application.properties file
        config.setPort(redisPort.get());
        config.setPassword(redisPassword);

        
        final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();    // Creating the client and factory
        final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);
        jedisFac.afterPropertiesSet();
        logger.info("redis host port> {redisHost} {redisPort}", redisHost, redisPort);

        RedisTemplate<String, Object> template = new RedisTemplate();       // create the template with the client
        template.setConnectionFactory(jedisFac);  // template is a Helper class that simplifies Redis data access code. 
        //Performs automatic serialization/deserialization between the given objects and the underlying binary data in the Redis store
        template.setKeySerializer(new StringRedisSerializer());     // keys are in UTF-8
        RedisSerializer<Object> serializer = (new JdkSerializationRedisSerializer(getClass().getClassLoader()));    
        // Delegates to the default (Java based) serializer and DefaultDeserializer. 
        template.setValueSerializer(serializer);        

        return template;

    }

}
