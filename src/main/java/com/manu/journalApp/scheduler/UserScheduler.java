package com.manu.journalApp.scheduler;

import com.manu.journalApp.entity.JournalEntry;
import com.manu.journalApp.entity.User;
import com.manu.journalApp.repository.UserRepoImpl;
import com.manu.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

@Component
public class UserScheduler {

    @Autowired
    public UserScheduler(EmailService emailService, UserRepoImpl userRepo) {
        this.emailService = emailService;
        this.userRepo = userRepo;
    }

    private final UserRepoImpl userRepo;
    private final EmailService emailService;

    @Scheduled(cron = "* * 9 * * SUN")
    public void fetchUserAndSaMail(){
        List<User> users = userRepo.getUserForSA();
        users.forEach(user->{
            List<String> journalEntries = user.getJournalEntries().stream()
                    .filter(entry -> entry
                            .getDate()
                            .isAfter(LocalDateTime.now(ZoneId.of("UTC")).minusDays(7)))
                    .map(JournalEntry::getContent).toList();

            emailService.sendEmail(user.getEmail(),"SENTIMENT ANALYSIS",journalEntries.toString());
        });
    }
}
