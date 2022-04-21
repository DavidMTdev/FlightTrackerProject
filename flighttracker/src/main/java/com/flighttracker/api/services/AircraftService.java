package com.flighttracker.api.services;

import com.flighttracker.api.entities.Aircraft;
import com.flighttracker.api.repositories.AircraftRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftService {
    
    @Autowired
    AircraftRepository aircraftRepository;

    public void create(Aircraft aircraft) {
        Aircraft a = aircraftRepository.findByNumber(aircraft.getNumber());

        if (a == null) {
            aircraftRepository.save(aircraft);
        }

    }

    public List<Aircraft> allAircraft(){
        List<Aircraft> a = aircraftRepository.findAll();

        return a;
    }
}