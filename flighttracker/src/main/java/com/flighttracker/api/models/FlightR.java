package com.flighttracker.api.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class FlightR implements Serializable {
    
    private Long id;
    
    private String number;

    private List<FlightHistoryR> histories;

    private FlightHistoryR history;

}
