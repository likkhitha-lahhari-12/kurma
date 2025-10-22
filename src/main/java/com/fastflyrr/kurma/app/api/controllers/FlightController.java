package com.fastflyrr.kurma.app.api.controllers;

import com.fastflyrr.kurma.app.api.dto.FlightRequestDto;
import com.fastflyrr.kurma.app.api.dto.FlightResponseDto;
import com.fastflyrr.kurma.app.services.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    //create a flight
    @PostMapping("/add")
    public ResponseEntity<FlightResponseDto> createFlight(@Valid @RequestBody FlightRequestDto flightRequestDto){
        return ResponseEntity.ok(flightService.createFlight(flightRequestDto));
    }

    //get all flights
    @GetMapping
    public ResponseEntity<List<FlightResponseDto>> getAllFlight(){
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    //get flight by id
    @GetMapping("/{flightId}")
    public ResponseEntity<FlightResponseDto> getFlightById(@PathVariable String flightId){
       return ResponseEntity.ok(flightService.getFlightById(flightId));
    }

    //update flight by id
    @PutMapping("/{flightId}")
    public ResponseEntity<FlightResponseDto> updateFlight(@PathVariable String id,
                                                          @RequestBody FlightRequestDto request){
        return ResponseEntity.ok(flightService.updateFlight(id, request));
    }

    //delete a flight
    @DeleteMapping("/{flightId}")
    public ResponseEntity<Void>  deleteFlight(@PathVariable String flightId){
        flightService.deleteFlight(flightId);
        return ResponseEntity.noContent().build();
    }

    //health check/ test endpoint
    @GetMapping("/ping")
    public String ping(){
        return "Flight service is running!";
    }
}
