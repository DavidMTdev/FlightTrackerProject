package com.flighttracker.api.services;

import java.sql.Timestamp;
import java.util.List;

import com.flighttracker.api.entities.FlightHistory;
import com.flighttracker.api.repositories.FlightHistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightHistoryService {
    
    @Autowired
    FlightHistoryRepository flightHistoryRepository;

    public void create(FlightHistory flightHistory) {

        if (flightHistory.getFlight() != null) {
            flightHistoryRepository.save(flightHistory);
        }
    }

    public List<Timestamp> getTimes() {
        return flightHistoryRepository.findDistinctTimes();
    }
}
