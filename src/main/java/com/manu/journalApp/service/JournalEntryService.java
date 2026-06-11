package com.manu.journalApp.service;

import com.manu.journalApp.entity.JournalEntry;
import com.manu.journalApp.entity.User;
import com.manu.journalApp.repository.JournalEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    /**
     * Find all journal entries of specific user
     *
     * @param userName username of that specific user
     * @return list of all journal entries of that user
     */
    public List<JournalEntry> getJournalEntriesByUserName(String userName) {
        User user = userService.findByUserName(userName);
        return user.getJournalEntries();

    }

    /**
     * Adds the provided journal entry to the specified user
     *
     * @param journalEntry journal entry to add
     * @param userName     username of user where the entry will add
     */
    public void save(JournalEntry journalEntry, String userName) {
        User user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry savedJournalEntry = journalEntryRepo.save(journalEntry);
        user.getJournalEntries().add(savedJournalEntry);
        userService.save(user);

    }

    public Optional<JournalEntry> getById(String jid) {
        return journalEntryRepo.findById(jid);

    }

    public void deleteAll() {
        journalEntryRepo.deleteAll();
    }

    public void updateJournal(JournalEntry journalEntry) {
        Optional<JournalEntry> old = journalEntryRepo.findById(journalEntry.getId());
        old.ifPresent(entry -> {
            entry.setTitle(journalEntry.getTitle() != null && !journalEntry.getTitle().isBlank() ? journalEntry.getTitle() : entry.getTitle());
            entry.setContent(journalEntry.getContent() != null && !journalEntry.getContent().isBlank() ? journalEntry.getContent() : entry.getContent());
            journalEntryRepo.save(entry);
        });
    }

    public void deleteById(String jid) {
        journalEntryRepo.deleteById(jid);
    }
}
