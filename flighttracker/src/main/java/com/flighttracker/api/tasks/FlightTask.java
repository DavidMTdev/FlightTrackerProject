package com.flighttracker.api.tasks;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.flighttracker.api.dto.FlightStatesDTO;
import com.flighttracker.api.models.Aircraft;
import com.flighttracker.api.models.Flight;
import com.flighttracker.api.models.FlightHistory;
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

    private static final Logger log = LoggerFactory.getLogger(FlightTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private AircraftService aircraftService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightHistoryService flightHistoryService;
    
    @Scheduled(cron = "*/30 * * * * *")
    public void launchTask() throws Exception {
        log.info("Current Time      : {}", dateFormat.format(new Date()));

        String url = "https://opensky-network.org/api/states/all";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        FlightStatesDTO flightStates = new Gson().fromJson(response.body(), FlightStatesDTO.class);

        for (ArrayList state : flightStates.getStates()) {

            String icao24 = (String) state.get(0);
            String callsign = (String) state.get(1);

            Double aaa = (Double) state.get(3);

            Timestamp timePosition = new Timestamp(aaa.longValue());

            aaa = (Double) state.get(5);
            Float longitude = aaa.floatValue();
            
            aaa = (Double) state.get(6);
            Float latitude = aaa.floatValue();

            aaa = (Double) state.get(7);
            Float altitude = aaa.floatValue();


            Aircraft aircraft = new Aircraft();
            aircraft.setNumber(callsign);


            Flight flight = new Flight();
            flight.setAircraft(aircraft);
            flight.setNumber(icao24);

            FlightHistory flightHistory = new FlightHistory();
            flightHistory.setFlight(flight);
            flightHistory.setTime(timePosition);
            flightHistory.setLongitude(longitude);
            flightHistory.setLatitude(latitude);
            flightHistory.setAltitude(altitude);

            flight.getHistory().add(flightHistory);

            System.out.println(aircraft);
            System.out.println(flight);
            System.out.println(flightHistory);

            aircraftService.create(aircraft);
            flightService.create(flight);
            flightHistoryService.create(flightHistory);

        }

    }
}
