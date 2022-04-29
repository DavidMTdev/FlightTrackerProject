package com.flighttracker.api.services;

import com.flighttracker.api.entities.Flight;
import com.flighttracker.api.entities.FlightHistory;
import com.flighttracker.api.repositories.FlightHistoryRepository;

import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightHistoryService {
    
    @Autowired
    FlightHistoryRepository flightHistoryRepository;

    public void create(FlightHistory flightHistory) {

        if (flightHistory.getFlight() != null) {
            flightHistoryRepository.save(flightHistory);
        }
    }

    public List<FlightHistory> getAll(){
        return flightHistoryRepository.findAll();
    }

    public List<FlightHistory> getOne(Timestamp timestamp){

        return flightHistoryRepository.findByTime(timestamp);

    }

}
