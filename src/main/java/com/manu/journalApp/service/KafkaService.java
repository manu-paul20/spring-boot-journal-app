package com.manu.journalApp.service;

import com.manu.journalApp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaService {

    @KafkaListener(topics = "sample",groupId = "journal-app-group")
    public void consume(User user){
        log.info("CONSUMED USER DATA -> {}",user.getUserName());
    }
}
