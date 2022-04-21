package com.flighttracker.api.controllers;


import com.flighttracker.api.entities.Aircraft;
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
    public List<Aircraft> getAllAircraft(){

        return aircraftService.allAircraft();
    }

}
