package com.fastflyrr.kurma.core.db.repositories;

import com.fastflyrr.kurma.enums.FlightStatus;
import com.fastflyrr.kurma.enums.Priority;
import com.fastflyrr.kurma.core.models.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends MongoRepository<Flight, String> {
    List<Flight> findByPriority(Priority priority);
    List<Flight> findByStatus(FlightStatus status);
}
