package com.flighttracker.api.controllers;


import com.flighttracker.api.dto.transformer.AircraftTransformer;
import com.flighttracker.api.entities.Aircraft;
import com.flighttracker.api.models.AircraftR;
import com.flighttracker.api.services.AircraftService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AircraftController {

    @Autowired
    private AircraftService aircraftService;


    @GetMapping("/aircrafts")
    public List<AircraftR> getAllAircraft(HttpServletRequest request) {
        log.info("Request URL :: " + request.getRequestURL().toString());
        long startTime = System.currentTimeMillis();
        List<Aircraft> a = aircraftService.allAircraft();

        List<AircraftR> b = AircraftTransformer.transform(a);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        log.info("Time = " + duration);

        return b;
    }

    @GetMapping("/aircrafts/{id}")
    public List<AircraftR> getAircraftById(@PathVariable("id") int id) {

        return AircraftTransformer.transform(aircraftService.aircraftById(id));
    }


    @GetMapping("/aircrafts/desc")
    public List<AircraftR> getAircraftDesc(HttpServletRequest request) {
        log.info("Request URL :: " + request.getRequestURL().toString());
        long startTime = System.currentTimeMillis();

        List<AircraftR> aircraftR = AircraftTransformer.transformAircrafts(aircraftService.aircraftByHistory());

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        log.info("Time = " + duration);

        return aircraftR;
    }

    @GetMapping("/aircrafts/time/{timestamp}")
    public List<AircraftR> getAircraftByTime(@PathVariable("timestamp") long timestamp){
        Timestamp t = new Timestamp(timestamp);
        return AircraftTransformer.transform(aircraftService.aircraftsByTime(t));
    }

}
