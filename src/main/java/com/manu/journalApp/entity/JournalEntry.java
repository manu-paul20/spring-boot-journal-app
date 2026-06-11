package com.manu.journalApp.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class JournalEntry {
    @Id private long id;
    private String title;
    private String content;
    private LocalDateTime date;
}
