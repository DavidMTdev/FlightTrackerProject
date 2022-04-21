package com.flighttracker.api.repositories;

import com.flighttracker.api.entities.FlightHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightHistoryRepository extends JpaRepository<FlightHistory, Long> {



}
