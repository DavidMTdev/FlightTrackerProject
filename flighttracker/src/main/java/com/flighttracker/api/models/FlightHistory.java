package com.flighttracker.api.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(FlightHistory.PK.class)
public class FlightHistory implements Serializable {

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
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
