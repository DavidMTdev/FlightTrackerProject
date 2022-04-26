package com.flighttracker.api.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(FlightHistory.PK.class)
public class FlightHistory implements Serializable {

    @Column(name = "latitude", nullable = false)
    private Float latitude;

    @Column(name = "longitude", nullable = false)
    private Float longitude;

    @Column(name = "altitude")
    private Float altitude;

    @Id
    private Timestamp time;

    @Id
    @ManyToOne
    private Flight flight;

    public static class PK implements Serializable
    {
        Long flight;
        Timestamp time;
    }
    
}
