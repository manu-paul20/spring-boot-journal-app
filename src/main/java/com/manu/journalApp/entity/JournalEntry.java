package com.manu.journalApp.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id private String id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
}
