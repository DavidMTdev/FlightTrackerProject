package com.flighttracker.api.repositories;

import com.flighttracker.api.models.Aircraft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
    
    Aircraft findByNumber(String number);
    
}
