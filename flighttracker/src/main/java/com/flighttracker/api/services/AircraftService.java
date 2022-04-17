package com.flighttracker.api.services;

import com.flighttracker.api.models.Aircraft;
import com.flighttracker.api.repositories.AircraftRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
