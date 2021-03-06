package com.flighttracker.api.dto.transformer;


import com.flighttracker.api.entities.Aircraft;
import com.flighttracker.api.models.AircraftR;

import java.util.ArrayList;
import java.util.List;

public class AircraftTransformer {

    public static List<AircraftR> transform(List<Aircraft> aircraft){

        List<AircraftR> aircraftRS = new ArrayList<>();

        for (Aircraft a: aircraft) {
            AircraftR aircraftR = new AircraftR();

            aircraftR.setId(a.getId());
            aircraftR.setModel(a.getModel());
            aircraftR.setNumber(a.getNumber());
            // aircraftR.setFlights(FlightTransformer.transformFlight(a.getFlights()));
            if (!a.getFlights().isEmpty()) {
                aircraftR.setFlight(FlightTransformer.transform(a.getFlights().get(a.getFlights().size() - 1)));
            }

            aircraftRS.add(aircraftR);
        }

       return aircraftRS;
    }

    public static List<AircraftR> transformAircrafts(List<Aircraft> aircraft){

        List<AircraftR> aircraftRS = new ArrayList<>();

        for (Aircraft a: aircraft) {
            AircraftR aircraftR = new AircraftR();

            aircraftR.setId(a.getId());
            aircraftR.setModel(a.getModel());
            aircraftR.setNumber(a.getNumber());
            aircraftR.setFlights(FlightTransformer.transformFlight(a.getFlights()));

            aircraftRS.add(aircraftR);
        }

       return aircraftRS;
    }
}
