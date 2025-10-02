package com.fastflyrr.kurma.controllers;

import com.fastflyrr.kurma.models.Flight;
import com.fastflyrr.kurma.models.Pad;
import com.fastflyrr.kurma.repositories.FlightRepository;
import com.fastflyrr.kurma.services.PadAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pad-assignment")
public class PadAssignmentController {
    private final PadAssignmentService padAssignmentService;
    private final FlightRepository flightRepository;


    public PadAssignmentController(PadAssignmentService padAssignmentService, FlightRepository flightRepository) {
        this.padAssignmentService = padAssignmentService;
        this.flightRepository = flightRepository;
    }

    //assign a pad to a flight
    @PostMapping("/assign/{flightId}")
    public ResponseEntity<Pad> assignPadToFlight(@PathVariable String flightId){
        try{
            Pad assignedPad = padAssignmentService.assignPad(flightId);
            return ResponseEntity.ok(assignedPad);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        }
    }
}
