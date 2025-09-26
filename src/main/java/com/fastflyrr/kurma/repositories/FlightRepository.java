package com.fastflyrr.kurma.repositories;

import com.fastflyrr.kurma.enums.Priority;
import com.fastflyrr.kurma.models.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FlightRepository extends MongoRepository<Flight, String> {
    List<Flight> findByPriority(Priority priority);
    List<Flight> findByPadIdIsNull();
}
