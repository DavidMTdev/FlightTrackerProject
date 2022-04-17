package com.flighttracker.api.dto;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightStatesDTO {
    private Integer time;
    private ArrayList<ArrayList> states;
    
}
