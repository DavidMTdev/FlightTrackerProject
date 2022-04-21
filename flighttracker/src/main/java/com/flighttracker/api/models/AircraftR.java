package com.flighttracker.api.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
public class AircraftR implements Serializable {

    private Long id;

    private String number;

    private String model;

    private List<FlightR> flights;
}
