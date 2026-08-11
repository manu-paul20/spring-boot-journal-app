package com.manu.journalApp.scheduler;

import com.manu.journalApp.entity.JournalEntry;
import com.manu.journalApp.entity.User;
import com.manu.journalApp.repository.UserRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserScheduler {

    private final UserRepoImpl userRepo;
    private final KafkaTemplate<String, User> kafkaTemplate;
    @Autowired
    public UserScheduler(KafkaTemplate<String, User> kafkaTemplate, UserRepoImpl userRepo) {
        this.kafkaTemplate = kafkaTemplate;
        this.userRepo = userRepo;
    }

//    @Scheduled(cron = "0 * * * * *")
    public void fetchUserAndSaMail() {
        List<User> users = userRepo.getUserForSA();
        User user2 =  User.builder()
                .userName("Mili")
                .id("mili1")
                .password("pass")
                .roles(new ArrayList<>())
                .email("mili@gmail.com")
                .journalEntries(new ArrayList<>())
                .sentimentAnalysis(true)
                .build();
        kafkaTemplate.send("sample", user2.getUserName(), user2);
    }
}
