package com.flighttracker.api.dto.transformer;

import com.flighttracker.api.entities.Flight;
import com.flighttracker.api.models.FlightR;

import java.util.ArrayList;
import java.util.List;

public class FlightTransformer {

    public static List<FlightR> transformFlight(List<Flight> flight){

        List<FlightR> flightRS = new ArrayList<>();

        for (Flight f : flight) {
            FlightR flightR = new FlightR();

            flightR.setId(f.getId());
            flightR.setNumber(f.getNumber());
            flightR.setHistory(FlightHistoryTransformer.transformerFlightHistory(f.getHistory()));
            flightRS.add(flightR);
        }



        return flightRS;
    }
}
