package com.flighttracker.api.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Flight implements Serializable {
    
    private Long id;
    
    private String number;

    private List<FlightHistory> history;

    
}
