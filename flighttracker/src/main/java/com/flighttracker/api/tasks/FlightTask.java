package com.flighttracker.api.tasks;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.flighttracker.api.dto.FlightStatesDTO;
import com.flighttracker.api.dto.StatesDTO;
import com.flighttracker.api.entities.Aircraft;
import com.flighttracker.api.entities.Flight;
import com.flighttracker.api.entities.FlightHistory;
import com.flighttracker.api.services.AircraftService;
import com.flighttracker.api.services.FlightHistoryService;
import com.flighttracker.api.services.FlightService;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FlightTask {

    @Autowired
    private AircraftService aircraftService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightHistoryService flightHistoryService;
    
    @Scheduled(cron = "0 */1 * * * *")
    public void launchTask() throws Exception {
        log.info("Start Launch Task");
        long startTime = System.currentTimeMillis();

        String url = "https://opensky-network.org/api/states/all";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        FlightStatesDTO flightStates = new Gson().fromJson(response.body(), FlightStatesDTO.class);

        ExecutorService executorService = Executors.newCachedThreadPool();

        int count = 0;
        System.out.println(flightStates.getStates().size());
        for (ArrayList<?> state : flightStates.getStates()) {
            if (count == 100) break; 

            Runnable worker = new Runnable() {
                @Override
                public void run() {
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
                    
                    if (statesDTO.getLatitude() != null && statesDTO.getLongitude() != null) {
                        flightHistory.setLongitude(statesDTO.getLongitude());
                        flightHistory.setLatitude(statesDTO.getLatitude());
                        flightHistory.setAltitude(statesDTO.getAltitude());

                        flight.getHistory().add(flightHistory);
                        flightHistoryService.create(flightHistory);
                    }
                }
            };

            executorService.execute(worker); 
            count ++;
        }

        executorService.shutdown();  

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        log.info("Time = " + duration);
        log.info("End Launch Task");
    }
}
