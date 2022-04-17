package com.flighttracker.api.services;

import com.flighttracker.api.models.Flight;
import com.flighttracker.api.models.FlightHistory;
import com.flighttracker.api.repositories.FlightRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    public void create(Flight flight) {
        Flight f = flightRepository.findByNumber(flight.getNumber());

        if (f == null) {
            flightRepository.save(flight);
        }
    }
    
}
