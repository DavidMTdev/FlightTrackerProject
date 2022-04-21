package com.flighttracker.api.dto;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightStatesDTO {
    private Integer time;
    private ArrayList<ArrayList<?>> states;
    
    public FlightStatesDTO(Integer time, ArrayList<ArrayList<?>> states) {
        this.time = time;
        this.states = states;
    }
    
}
