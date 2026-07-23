package com.manu.journalApp.apiresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse{
    @JsonProperty("Temperature")
    public Temperature temperature;



    public class Temperature{
        @JsonProperty("Metric")
        public Metric metric;
        @JsonProperty("Imperial")
        public Imperial imperial;

        public class Imperial{
            @JsonProperty("Value")
            public int value;
            @JsonProperty("Unit")
            public String unit;
            @JsonProperty("UnitType")
            public int unitType;
        }

        public class Metric{
            @JsonProperty("Value")
            public double value;
            @JsonProperty("Unit")
            public String unit;
            @JsonProperty("UnitType")
            public int unitType;
        }
    }



}

