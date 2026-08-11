package com.manu.journalApp;

import com.manu.journalApp.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;

@SpringBootTest
public class KafkaTest {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    @Test
    @Disabled
    public void send(){
        User user1 = User.builder()
                .userName("Manu")
                .id("manu1")
                .password("pass")
                .roles(new ArrayList<>())
                .email("manu@gmail.com")
                .journalEntries(new ArrayList<>())
                .sentimentAnalysis(true)
                .build();
        User user2 =  User.builder()
                .userName("Mili")
                .id("mili1")
                .password("pass")
                .roles(new ArrayList<>())
                .email("mili@gmail.com")
                .journalEntries(new ArrayList<>())
                .sentimentAnalysis(true)
                .build();
        kafkaTemplate.send("sample",user1.getUserName(),user1);
        kafkaTemplate.send("sample",user2.getUserName(),user2);

    }
}
