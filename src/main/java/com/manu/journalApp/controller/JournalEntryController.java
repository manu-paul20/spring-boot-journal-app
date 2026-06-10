package com.manu.journalApp.controller;

import com.manu.journalApp.entity.JournalEntry;
import com.manu.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll() {
       return journalEntryService.getAllJournalEntries();
    }

    @GetMapping("/id/{jid}")
    public JournalEntry getById(@PathVariable Long jid) {
        return journalEntryService.getById(jid).orElse(null);
    }

    @PostMapping
    public void postJournal(@RequestBody JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryService.addEntry(journalEntry);
    }

    @PutMapping
    public void updateJournal(@RequestBody JournalEntry journalEntry) {
        journalEntryService.updateJournal(journalEntry);
    }

    @DeleteMapping("/id/{jid}")
    public void deleteJournal(@PathVariable Long jid) {
        journalEntryService.deleteById(jid);
    }

    @DeleteMapping("/delete/all")
    public void deleteAll(){
        journalEntryService.deleteAll();
    }

}
