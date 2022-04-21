package com.flighttracker.api.services;

import com.flighttracker.api.entities.Flight;
import com.flighttracker.api.repositories.FlightRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    public Flight create(Flight flight) {
        Flight f = flightRepository.findByNumber(flight.getNumber());

        if (f == null) return flightRepository.save(flight);
        return f;
    }
    
}
