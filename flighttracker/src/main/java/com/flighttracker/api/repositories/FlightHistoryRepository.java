package com.flighttracker.api.repositories;

import java.sql.Timestamp;
import java.util.List;

import com.flighttracker.api.models.Flight;
import com.flighttracker.api.models.FlightHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightHistoryRepository extends JpaRepository<FlightHistory, Long> {



}
