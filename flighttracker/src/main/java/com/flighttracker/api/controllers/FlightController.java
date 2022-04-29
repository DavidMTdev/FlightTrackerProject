package com.flighttracker.api.controllers;

import com.flighttracker.api.dto.transformer.FlightTransformer;
import com.flighttracker.api.models.FlightR;
import com.flighttracker.api.services.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/flights")
    public List<FlightR> getAllFlights(){

        return FlightTransformer.transformFlight(flightService.AllFlight());
    }

    @GetMapping("/flights/{id}")
    public List<FlightR> getAllFlightById(@PathVariable("id") int id){

        return FlightTransformer.transformFlight(flightService.FlightById(id));
    }
}
