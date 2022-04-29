package com.flighttracker.api.repositories;

import com.flighttracker.api.entities.Aircraft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
    
    Aircraft findByNumber(String number);

    @Query(value = "Select * from aircraft a, flight_history h, flight f where h.time = ?1 and h.flight_id = f.id and f.aircraft_id = a.id", nativeQuery = true)
    List<Aircraft> findByTimes(Timestamp timestamp);

}
