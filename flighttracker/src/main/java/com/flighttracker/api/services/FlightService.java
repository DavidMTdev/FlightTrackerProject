package com.flighttracker.api.services;

import com.flighttracker.api.entities.Aircraft;
import com.flighttracker.api.entities.Flight;
import com.flighttracker.api.models.FlightR;
import com.flighttracker.api.repositories.FlightRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    public Flight create(Flight flight) {
        Flight f = flightRepository.findByNumber(flight.getNumber());

        if (f == null) return flightRepository.save(flight);
        return f;
    }

    public List<Flight> AllFlight(){

        return flightRepository.findAll();
    }

    public List<Flight> FlightById(long id){
        Optional<Flight> f = flightRepository.findById(id);

        return f.stream().collect(Collectors.toList());
    }
    
}
