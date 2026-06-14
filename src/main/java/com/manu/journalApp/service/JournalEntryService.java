package com.manu.journalApp.service;

import com.manu.journalApp.entity.JournalEntry;
import com.manu.journalApp.entity.User;
import com.manu.journalApp.exception.InvalidJournalIdException;
import com.manu.journalApp.exception.UserNotFoundException;
import com.manu.journalApp.repository.JournalEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
    public List<JournalEntry> getJournalEntriesByUserName(String userName) throws UserNotFoundException {
        User user = userService.findByUserName(userName);
        if (user != null) {
            return user.getJournalEntries();
        } else {
            throw new UserNotFoundException();
        }


    }

    /**
     * Adds the provided journal entry to the specified user
     *
     * @param journalEntry journal entry to add
     * @param userName     username of user where the entry will add
     */
    @Transactional
    public void save(JournalEntry journalEntry, String userName) {
        User user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry savedJournalEntry = journalEntryRepo.save(journalEntry);
        user.getJournalEntries().add(savedJournalEntry);
        userService.save(user);

    }

    public JournalEntry getById(String userName, String jid) throws InvalidJournalIdException {
        User user = userService.findByUserName(userName);
        boolean isJournalPresent = user.getJournalEntries().stream().anyMatch(entry -> entry.getId().equals(jid));
        if (isJournalPresent) {
            return journalEntryRepo.findById(jid).get();
        } else {
            throw new InvalidJournalIdException();
        }
    }

    public void deleteAll() {
        journalEntryRepo.deleteAll();
    }

    @Transactional
    public void updateJournal(
            JournalEntry newEntry,
            String jid,
            String userName
    ) throws InvalidJournalIdException {


        User user = userService.findByUserName(userName);
        List<JournalEntry> entries = user.getJournalEntries().stream().filter(entry -> entry.getId().equals(jid)).toList();
        if (!entries.isEmpty()) {
            JournalEntry oldEntry = entries.getFirst();
            oldEntry.setTitle((newEntry.getTitle().isBlank()) ? oldEntry.getTitle() : newEntry.getTitle());
            oldEntry.setContent((newEntry.getContent().isBlank()) ? oldEntry.getContent() : newEntry.getContent());
            journalEntryRepo.save(oldEntry);
        } else {
            throw new InvalidJournalIdException();
        }

    }

    @Transactional
    public void deleteById(String jid, String userName) throws InvalidJournalIdException {
        User user = userService.findByUserName(userName);
        List<JournalEntry> entries = user.getJournalEntries().stream().filter(entry->entry.getId().equals(jid)).toList();
        if(!entries.isEmpty()) {
            user.getJournalEntries().remove(entries.getFirst());
            userService.save(user);
            journalEntryRepo.deleteById(jid);
        }else{
            throw new InvalidJournalIdException();
        }

    }
}
