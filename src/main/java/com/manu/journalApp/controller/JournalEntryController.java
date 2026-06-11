package com.manu.journalApp.controller;

import com.manu.journalApp.entity.JournalEntry;
import com.manu.journalApp.entity.User;
import com.manu.journalApp.service.JournalEntryService;
import com.manu.journalApp.service.UserService;
import jdk.jfr.Experimental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
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

    @GetMapping("{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser(@PathVariable String userName) {
        List<JournalEntry> journalEntries = journalEntryService.getJournalEntriesByUserName(userName);
        if(journalEntries!=null && !journalEntries.isEmpty()){
            return new ResponseEntity<>(journalEntries,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/id/{jid}")
    public ResponseEntity<JournalEntry> getById(@PathVariable String jid) {
        Optional<JournalEntry> entry = journalEntryService.getById(jid);
        if(entry.isPresent()){
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{userName}")
    public ResponseEntity<?> postJournal(@RequestBody JournalEntry journalEntry,@PathVariable String userName) {
       try {
           journalEntryService.save(journalEntry,userName);
           return new ResponseEntity<>(HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping
    public ResponseEntity<?> updateJournal(@RequestBody JournalEntry journalEntry) {
        journalEntryService.updateJournal(journalEntry);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/id/{jid}")
    public ResponseEntity<?> deleteJournal(@PathVariable String jid) {
        if (journalEntryService.getById(jid).isPresent()){
            journalEntryService.deleteById(jid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * deletes all entries from journalEntry collection.
     *  <br>Only for testing*/
    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAll(){
        journalEntryService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
