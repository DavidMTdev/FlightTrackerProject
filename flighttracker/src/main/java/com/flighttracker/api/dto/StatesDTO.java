package com.flighttracker.api.dto;

import java.sql.Timestamp;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatesDTO {
    private String icao24;
    private String callsign;
    private String originCountry;
    private Timestamp timePosition;
    private Float longitude;
    private Float latitude;
    private Float altitude;

    public StatesDTO(ArrayList<?> state) {
        icao24 = (String) state.get(0);
        callsign = (String) state.get(1);
        originCountry = (String) state.get(2);
        timePosition = new Timestamp(secondToMillis(doubleToLong((Double) state.get(3))));
        longitude = doubleToFloat((Double) state.get(5));
        latitude = doubleToFloat((Double) state.get(6));
        altitude = doubleToFloat((Double) state.get(7));
    }

    public Float doubleToFloat(Double value) {
        if (value != null) return value.floatValue();
        return null;
    }

    public Long doubleToLong(Double value) {
        if (value != null) return value.longValue();
        return null;
    }

    public Long secondToMillis(Long value) {
        if (value != null) return value * 1000;
        return System.currentTimeMillis();
    }
}
