package com.flighttracker.api.controllers;


import com.flighttracker.api.dto.transformer.FlightHistoryTransformer;
import com.flighttracker.api.entities.FlightHistory;
import com.flighttracker.api.models.FlightHistoryR;
import com.flighttracker.api.services.FlightHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FlightHistoryController {

    @Autowired
    private FlightHistoryService flightHistoryService;

    @GetMapping("/history")
    public List<FlightHistoryR> getAllHistory(){
        return FlightHistoryTransformer.transformerFlightHistory(flightHistoryService.getAll());
    }

    @GetMapping("/history/{time}")
    public List<FlightHistoryR> getHistoryById(@PathVariable("time") Timestamp time){
        return FlightHistoryTransformer.transformerFlightHistory(flightHistoryService.getOne(time));
    }

}
