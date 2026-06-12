package com.manu.journalApp.controller;

import com.manu.journalApp.entity.JournalEntry;
import com.manu.journalApp.exception.UserNotFoundException;
import com.manu.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser(@PathVariable String userName) {
        try {
            List<JournalEntry> journalEntries = journalEntryService.getJournalEntriesByUserName(userName);
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/id/{jid}")
    public ResponseEntity<JournalEntry> getById(@PathVariable String jid) {
        Optional<JournalEntry> entry = journalEntryService.getById(jid);
        if (entry.isPresent()) {
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{userName}")
    public ResponseEntity<?> postJournal(@RequestBody JournalEntry journalEntry, @PathVariable String userName) {
        try {
            journalEntryService.save(journalEntry, userName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userName}/{jid}")
    public ResponseEntity<?> updateJournal(
            @RequestBody JournalEntry journalEntry,
            @PathVariable String jid,
            @PathVariable String userName
    ) {
        try {
            journalEntryService.updateJournal(journalEntry, jid, userName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userName}/{jid}")
    public ResponseEntity<?> deleteJournal(@PathVariable String jid, @PathVariable String userName) {
        try {
            journalEntryService.deleteById(jid, userName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * deletes all entries from journalEntry collection.
     * <br>Only for testing
     */
    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAll() {
        journalEntryService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
