package com.manu.journalApp.controller;

import com.manu.journalApp.entity.JournalEntry;
import com.manu.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


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
    public ResponseEntity<JournalEntry> getById(@PathVariable Long jid) {
        Optional<JournalEntry> entry = journalEntryService.getById(jid);
        if(entry.isPresent()){
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> postJournal(@RequestBody JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryService.addEntry(journalEntry);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateJournal(@RequestBody JournalEntry journalEntry) {
        journalEntryService.updateJournal(journalEntry);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/id/{jid}")
    public ResponseEntity<?> deleteJournal(@PathVariable Long jid) {
        if (journalEntryService.getById(jid).isPresent()){
            journalEntryService.deleteById(jid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAll(){
        journalEntryService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
