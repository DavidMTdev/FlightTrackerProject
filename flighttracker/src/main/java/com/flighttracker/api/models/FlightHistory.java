package com.flighttracker.api.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;


@Getter
@Setter
public class FlightHistory implements Serializable {

    private Float latitude;

    private Float longitude;

    private Float altitude;

    private Timestamp time;
    
}
