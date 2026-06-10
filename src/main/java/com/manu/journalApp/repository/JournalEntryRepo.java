package com.manu.journalApp.repository;

import com.manu.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;


public interface JournalEntryRepo extends MongoRepository<JournalEntry, Long> { }
