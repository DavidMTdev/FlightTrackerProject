package com.flighttracker.api.repositories;

import com.flighttracker.api.entities.FlightHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface FlightHistoryRepository extends JpaRepository<FlightHistory, Long> {

    List<FlightHistory> findByTime(Timestamp time);

}
