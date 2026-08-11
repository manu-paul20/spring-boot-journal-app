package com.manu.journalApp;

import com.manu.journalApp.entity.User;
import com.manu.journalApp.service.RedisService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RedisCloudTest {

    @Autowired
    private RedisService redisService;

    @Test
    @Disabled
    public void test(){
//        redisService.delete("manu");
        makeRequest("manu");
        makeRequest("manu");
    }
    public void makeRequest(String userName){
        User redisResponse = redisService.get(userName, User.class);
        if(redisResponse == null){
            User networkResponse = User.builder()
                    .userName(userName)
                    .password("pass")
                    .email(userName+"@gmail.com")
                    .id(userName+1)
                    .journalEntries(new ArrayList<>())
                    .roles(List.of("USER","ADMIN"))
                    .sentimentAnalysis(false)
                    .build();
            redisService.set(userName,networkResponse);
        }
    }
}
