package com.manu.journalApp.cache;

import com.manu.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AppCache {
    public Map<String,String> APP_CACHE = new HashMap<>();

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    @PostConstruct
    public void init(){
        var config = configJournalAppRepository.findAll();
        config.forEach(configEntity->APP_CACHE.put(configEntity.getKey(),configEntity.getValue()));
    }
}
