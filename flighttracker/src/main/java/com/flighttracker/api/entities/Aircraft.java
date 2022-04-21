package com.flighttracker.api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Aircraft implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "number", unique=true)
    private String number;

    @Column(name = "model")
    private String model;

    @OneToMany(mappedBy = "aircraft", fetch = FetchType.EAGER)
    private List<Flight> flights = new ArrayList<>();
}
