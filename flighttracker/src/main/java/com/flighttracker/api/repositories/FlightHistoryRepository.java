package com.flighttracker.api.repositories;

import java.sql.Timestamp;
import java.util.List;

import com.flighttracker.api.entities.FlightHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightHistoryRepository extends JpaRepository<FlightHistory, Long> {

    List<FlightHistory> findByTime(Timestamp time);

    @Query(value = "Select distinct time from flight_history", nativeQuery = true)
    List<Timestamp> findDistinctTimes();
}
