package com.manu.journalApp.service;

import com.manu.journalApp.entity.JournalEntry;
import com.manu.journalApp.entity.User;
import com.manu.journalApp.exception.UserNotFoundException;
import com.manu.journalApp.repository.JournalEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    public List<JournalEntry> getJournalEntriesByUserName(String userName) throws UserNotFoundException {
        User user = userService.findByUserName(userName);
        if (user != null){
            return user.getJournalEntries();
        }else {
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

    public Optional<JournalEntry> getById(String jid) {
        return journalEntryRepo.findById(jid);

    }

    public void deleteAll() {
        journalEntryRepo.deleteAll();
    }

    public void updateJournal(
            JournalEntry newEntry,
            String jid,
            String userName
            ) throws UserNotFoundException {
        Optional<JournalEntry> old = journalEntryRepo.findById(jid);
        User user = userService.findByUserName(userName);
        if(user!=null){
            old.ifPresent(oldEntry->{
                oldEntry.setTitle((newEntry.getTitle().isBlank())?oldEntry.getTitle() : newEntry.getTitle());
                oldEntry.setContent((newEntry.getContent().isBlank())?oldEntry.getContent() : newEntry.getContent());
                journalEntryRepo.save(oldEntry);
            });
        }else {
            throw new UserNotFoundException();
        }
    }

    @Transactional
    public void deleteById(String jid,String userName) {
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(entry->entry.getId().equals(jid));
        userService.save(user);
        journalEntryRepo.deleteById(jid);
    }
}
