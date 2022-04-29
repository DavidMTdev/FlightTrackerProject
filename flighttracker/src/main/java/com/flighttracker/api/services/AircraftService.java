package com.flighttracker.api.services;

import com.flighttracker.api.entities.Aircraft;
import com.flighttracker.api.repositories.AircraftRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AircraftService {
    
    @Autowired
    AircraftRepository aircraftRepository;

    public Aircraft create(Aircraft aircraft) {
        Aircraft a = aircraftRepository.findByNumber(aircraft.getNumber());

        if (a == null) return aircraftRepository.save(aircraft);
        return a;
    }

    public List<Aircraft> allAircraft(){
        return aircraftRepository.findAll();
    }

    public List<Aircraft> aircraftById(long id){
        Optional<Aircraft> o = aircraftRepository.findById(id);

        return o.stream().collect(Collectors.toList());
    }

    public List<Aircraft> aircraftByHistory(){
        
        return aircraftRepository.findAll(Sort.by(Sort.Order.desc("flights.history.time")));
    }

    public List<Aircraft> aircraftsByTime(Timestamp timestamp){
        return aircraftRepository.findByTimes(timestamp);
    }
}
