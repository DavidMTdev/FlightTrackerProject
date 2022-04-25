package com.flighttracker.api.services;

import com.flighttracker.api.entities.Aircraft;
import com.flighttracker.api.repositories.AircraftRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.OrderBy;
import java.util.ArrayList;
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
        List<Aircraft> a = aircraftRepository.findAll();

        return a;
    }

    public List<Aircraft> aircraftById(long id){
        Optional<Aircraft> o = aircraftRepository.findById(id);



        List<Aircraft> a = o.stream().collect(Collectors.toList());

        return a;
    }

    public List<Aircraft> aircraftByHistory(){

        List<Aircraft> a = aircraftRepository.findAll(Sort.by(Sort.Order.desc("flights.history.time")));



        return a;

    }
}
