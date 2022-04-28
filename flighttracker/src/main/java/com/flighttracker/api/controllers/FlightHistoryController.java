package com.flighttracker.api.controllers;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.flighttracker.api.entities.FlightHistory;
import com.flighttracker.api.services.FlightHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FlightHistoryController {

    @Autowired
    private FlightHistoryService flightHistoryService;

    @GetMapping("/history/times")
    public List<Timestamp> getAllAircraft(HttpServletRequest request) {
        log.info("Request URL :: " + request.getRequestURL().toString());
        long startTime = System.currentTimeMillis();

        List<Timestamp> b = flightHistoryService.getTimes();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        log.info("Time = " + duration);

        return b;
    }
}
