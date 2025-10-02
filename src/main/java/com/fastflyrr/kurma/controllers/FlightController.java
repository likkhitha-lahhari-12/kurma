package com.fastflyrr.kurma.controllers;

import com.fastflyrr.kurma.dto.FlightRequestDto;
import com.fastflyrr.kurma.dto.FlightResponseDto;
import com.fastflyrr.kurma.mappers.FlightMapper;
import com.fastflyrr.kurma.models.Flight;
import com.fastflyrr.kurma.repositories.FlightRepository;
import com.fastflyrr.kurma.services.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;
   // private final FlightMapper flightMapper;
    private final FlightRepository flightRepository;

    public FlightController(FlightService flightService, FlightMapper flightMapper, FlightRepository flightRepository) {
        this.flightService = flightService;
        this.flightRepository = flightRepository;
       // this.flightMapper = flightMapper;
    }


    //create a flight
    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight){
        Flight savedFlight = flightRepository.save(flight);
        return ResponseEntity.ok(savedFlight);
    }

    //get all flights


    //assign a pad to a flight
//    @PostMapping("/{flightId}/assign-pad")
//    public ResponseEntity<FlightResponseDto> assignPad(@PathVariable String flightId) {
//        Flight updated = flightService.assignPad(flightId);
//        return ResponseEntity.ok(flightMapper.toDto(updated));
//    }

    //health check/ test endpoint
    @GetMapping("/ping")
    public String ping(){
        return "Flight service is running!";
    }
}
