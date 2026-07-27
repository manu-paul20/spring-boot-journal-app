package com.manu.journalApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class RedisService {

    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private RedisTemplate<String,String> redisTemplate;

    public  void set(String key,Object value){
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json =  mapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key,json);
        } catch (Exception e) {
            log.error("ERROR = {}",e.getMessage());
        }
    }

    public <T> T get(String key,Class<T> entityClass){
        try {
            String value =  redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(value, entityClass);
        } catch (Exception e) {
            log.error("ERROR = {}",e.getMessage());
            return null;
        }
    }

    public void set(String key,Object value,Long expiry){
        try {
            ObjectMapper mapper = new ObjectMapper();
           String json =  mapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key,json,expiry);
        } catch (Exception e) {
            log.error("ERROR = {}",e.getMessage());
        }

    }
}
