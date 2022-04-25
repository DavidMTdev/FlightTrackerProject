package com.flighttracker.api.repositories;

import com.flighttracker.api.entities.Aircraft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
    
    Aircraft findByNumber(String number);

}
