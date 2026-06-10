package com.manu.journalApp.service;

import com.manu.journalApp.entity.JournalEntry;
import com.manu.journalApp.repository.JournalEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.rowset.Joinable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public void addEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAllJournalEntries(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> getById(Long jid){
       return  journalEntryRepo.findById(jid);

    }
    public void deleteAll(){
        journalEntryRepo.deleteAll();
    }

    public void updateJournal(JournalEntry journalEntry){
        Optional<JournalEntry> old = journalEntryRepo.findById(journalEntry.getId());
        old.ifPresent(entry->{
            entry.setTitle(journalEntry.getTitle()!=null && !journalEntry.getTitle().isBlank() ? journalEntry.getTitle() : entry.getTitle());
            entry.setContent(journalEntry.getContent()!=null && !journalEntry.getContent().isBlank() ? journalEntry.getContent() : entry.getContent());
            journalEntryRepo.save(entry);
        });
    }

    public void deleteById(Long jid){
        journalEntryRepo.deleteById(jid);
    }
}
