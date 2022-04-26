package com.flighttracker.api.dto.transformer;

import com.flighttracker.api.entities.FlightHistory;
import com.flighttracker.api.models.FlightHistoryR;

import java.util.ArrayList;
import java.util.List;

public class FlightHistoryTransformer {

    public static FlightHistoryR transformer(FlightHistory flightHistory) {

        FlightHistoryR flightHistoryR = new FlightHistoryR();

        flightHistoryR.setAltitude(flightHistory.getAltitude());
        flightHistoryR.setLatitude(flightHistory.getLatitude());
        flightHistoryR.setLongitude(flightHistory.getLongitude());
        flightHistoryR.setTime(flightHistory.getTime());

        return flightHistoryR;
    }

    public static List<FlightHistoryR> transformerFlightHistory(List<FlightHistory> flightHistory){

        List<FlightHistoryR> flightHistoryRS = new ArrayList<>();

        for (FlightHistory f: flightHistory ) {
            FlightHistoryR flightHistoryR = new FlightHistoryR();
            flightHistoryR.setAltitude(f.getAltitude());
            flightHistoryR.setLatitude(f.getLatitude());
            flightHistoryR.setLongitude(f.getLongitude());
            flightHistoryR.setTime(f.getTime());
            flightHistoryRS.add(flightHistoryR);
        }

        return flightHistoryRS;

    }
}
