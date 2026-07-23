package com.manu.journalApp.apiresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationResponse {
    @JsonProperty("Key")
    public String key;
}

