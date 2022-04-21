package com.flighttracker.api.controllers;


import com.flighttracker.api.dto.transformer.AircraftTransformer;
import com.flighttracker.api.models.AircraftR;
import com.flighttracker.api.services.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AircraftController {

    @Autowired
    private AircraftService aircraftService;

    @GetMapping("/aircrafts")
    public List<AircraftR> getAllAircraft(){


        return AircraftTransformer.transform(aircraftService.allAircraft());
    }

}
