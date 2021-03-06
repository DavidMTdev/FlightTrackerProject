package com.flighttracker.api.repositories;

import com.flighttracker.api.entities.Flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Flight findByNumber(String number);
}
