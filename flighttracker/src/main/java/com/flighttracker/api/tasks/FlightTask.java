package com.flighttracker.api.tasks;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import java.util.ArrayList;

import com.flighttracker.api.dto.FlightStatesDTO;
import com.flighttracker.api.dto.StatesDTO;
import com.flighttracker.api.entities.Aircraft;
import com.flighttracker.api.entities.Flight;
import com.flighttracker.api.entities.FlightHistory;
import com.flighttracker.api.services.AircraftService;
import com.flighttracker.api.services.FlightHistoryService;
import com.flighttracker.api.services.FlightService;
import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FlightTask {

    private static final Logger logger = LoggerFactory.getLogger(FlightTask.class);

    @Autowired
    private AircraftService aircraftService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightHistoryService flightHistoryService;
    
    @Scheduled(cron = "*/15 * * * * *")
    public void launchTask() throws Exception {
        logger.info("Start Launch Task");

        String url = "https://opensky-network.org/api/states/all";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        FlightStatesDTO flightStates = new Gson().fromJson(response.body(), FlightStatesDTO.class);

        for (ArrayList<?> state : flightStates.getStates()) {

            StatesDTO statesDTO = new StatesDTO(state);

            Aircraft aircraft = new Aircraft();
            aircraft.setNumber(statesDTO.getCallsign());

            aircraft = aircraftService.create(aircraft);

            Flight flight = new Flight();
            flight.setAircraft(aircraft);
            flight.setNumber(statesDTO.getIcao24());

            flight = flightService.create(flight);

            FlightHistory flightHistory = new FlightHistory();
            flightHistory.setFlight(flight);
            flightHistory.setTime(statesDTO.getTimePosition());
            flightHistory.setLongitude(statesDTO.getLongitude());
            flightHistory.setLatitude(statesDTO.getLatitude());
            flightHistory.setAltitude(statesDTO.getAltitude());

            flight.getHistory().add(flightHistory);
;
            
            flightHistoryService.create(flightHistory);
        }

        logger.info("End Launch Task");
    }
}
