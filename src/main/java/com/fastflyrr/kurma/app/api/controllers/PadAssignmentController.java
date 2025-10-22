package com.fastflyrr.kurma.app.api.controllers;

import com.fastflyrr.kurma.app.api.dto.PadAssignmentRequestDto;
import com.fastflyrr.kurma.app.api.dto.PadAssignmentResponseDto;
import com.fastflyrr.kurma.app.api.mappers.PadAssignmentMapper;
import com.fastflyrr.kurma.app.api.mappers.PadMapper;
import com.fastflyrr.kurma.core.db.repositories.FlightRepository;
import com.fastflyrr.kurma.app.services.PadAssignmentService;
import com.fastflyrr.kurma.core.models.PadAssignment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pad-assignment")
public class PadAssignmentController {

    private final PadAssignmentService padAssignmentService;

    public PadAssignmentController(PadAssignmentService padAssignmentService) {
        this.padAssignmentService = padAssignmentService;
    }

    // ---------------- CREATE / ASSIGN ----------------
    @PostMapping
    public ResponseEntity<PadAssignmentResponseDto> assignPadToFlight(@RequestBody PadAssignmentRequestDto dto) {
        PadAssignmentResponseDto response = padAssignmentService.assignPad(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ---------------- READ ----------------
    @GetMapping
    public ResponseEntity<List<PadAssignmentResponseDto>> getAllAssignments() {
        return ResponseEntity.ok(padAssignmentService.getAllAssignments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PadAssignmentResponseDto> getAssignmentById(@PathVariable String id) {
        return ResponseEntity.ok(padAssignmentService.getAssignmentById(id));
    }

    // ---------------- UPDATE ----------------
    @PutMapping("/{id}")
    public ResponseEntity<PadAssignmentResponseDto> updateAssignment(
            @PathVariable String id,
            @RequestBody PadAssignmentRequestDto dto
    ) {
        return ResponseEntity.ok(padAssignmentService.updateAssignment(id, dto));
    }

    // ---------------- DELETE ----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable String id) {
        padAssignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }

    // ---------------- COMPLETE ASSIGNMENT ----------------
    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> completeAssignment(@PathVariable String id) {
        padAssignmentService.completeAssignment(id);
        return ResponseEntity.ok().build();
    }
}
