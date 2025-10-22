package com.fastflyrr.kurma.app.services.Impl;

import com.fastflyrr.kurma.app.api.dto.PadAssignmentRequestDto;
import com.fastflyrr.kurma.app.api.dto.PadAssignmentResponseDto;
import com.fastflyrr.kurma.core.db.repositories.*;
import com.fastflyrr.kurma.core.models.*;
import com.fastflyrr.kurma.enums.FlightStatus;
import com.fastflyrr.kurma.enums.PadAssignmentStatus;
import com.fastflyrr.kurma.enums.PadStatus;
import com.fastflyrr.kurma.app.services.PadAssignmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PadAssignmentServiceImpl implements PadAssignmentService {

    private final PadRepository padRepository;
    private final FlightRepository flightRepository;
    private final PadAssignmentRepository padAssignmentRepository;
    private final EventOutboxRepository eventOutboxRepository;

    public PadAssignmentServiceImpl(PadRepository padRepository, FlightRepository flightRepository, PadAssignmentRepository padAssignmentRepository, EventOutboxRepository eventOutboxRepository) {
        this.padRepository = padRepository;
        this.flightRepository = flightRepository;
        this.padAssignmentRepository = padAssignmentRepository;
        this.eventOutboxRepository = eventOutboxRepository;
    }

    @Override
    @Transactional
    public void completeAssignment(String assignmentId) {
        PadAssignment assignment = padAssignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found: " + assignmentId));

        assignment.setStatus(PadAssignmentStatus.COMPLETED);
        padAssignmentRepository.save(assignment);

        Pad pad = padRepository.findById(assignment.getPadId())
                .orElseThrow(() -> new RuntimeException("Pad not found"));
        pad.setStatus(PadStatus.FREE);
        padRepository.save(pad);

        Flight flight = flightRepository.findById(assignment.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        flight.setStatus(FlightStatus.DEPARTED);
        flightRepository.save(flight);

        // emit release event
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("flightId", flight.getId());
        eventData.put("padId", pad.getId());
        eventData.put("vertiportId", pad.getVertiportId());
        eventData.put("assignmentId", assignment.getId());
        eventData.put("status", "released");

        EventOutbox event = new EventOutbox();
        event.setAggregateType("PadAssignment");
        event.setAggregateId(assignment.getId());
        event.setEventType("kurma.pad.released");
        event.setEventData(eventData);
        eventOutboxRepository.save(event);
    }


    @Override
    public PadAssignmentResponseDto assignPad(PadAssignmentRequestDto dto) {
        // check if flight exists
        Flight flight = flightRepository.findById(dto.getFlightId())
                .orElseThrow(()-> new RuntimeException("Flight not found with id: "+dto.getFlightId()));
        //prevent duplicate assignment for same flight
        boolean alreadyAssigned = padAssignmentRepository.existsByFlightIdAndStatusIn(dto.getFlightId(),
                List.of(PadAssignmentStatus.AWARDED, PadAssignmentStatus.CONFIRMED, PadAssignmentStatus.ACTIVE));
        if(alreadyAssigned){
            throw new RuntimeException("Flight already has an active pad assignment");
        }

        //find first free pad
        Pad pad = padRepository.findFirstByVertiportIdAndStatus(dto.getVertiportId(), PadStatus.FREE)
                .orElseThrow(()-> new RuntimeException("No free pads available for vertiport id: "+dto.getVertiportId()));

        //create padAssignment
        PadAssignment assignment = new PadAssignment();
        assignment.setFlightId(flight.getId());
        assignment.setVertiportId(dto.getVertiportId());
        assignment.setPadId(pad.getId());
        assignment.setStartAt(Instant.now());
        assignment.setEndAt(Instant.now().plus(30, ChronoUnit.MINUTES));
        assignment.setStatus(PadAssignmentStatus.AWARDED);

        padAssignmentRepository.save(assignment);

        //update and save pad and flight
        pad.setStatus(PadStatus.ASSIGNED);
        padRepository.save(pad);

        flight.setStatus(FlightStatus.ASSIGNED);
        flightRepository.save(flight);

        // create event (outbox pattern)
        Map<String , Object> eventData = new HashMap<>();
        eventData.put("flightId", flight.getId());
        eventData.put("padId", pad.getId());
        eventData.put("vertiportId", pad.getVertiportId());
        eventData.put("assignmentId", assignment.getId());
        eventData.put("status", assignment.getStatus());

        EventOutbox event = new EventOutbox();
        event.setAggregateType("PadAssignment");
        event.setAggregateId(assignment.getId());
        event.setEventType("kurma.pad.assigned");
        event.setEventData(eventData);
        eventOutboxRepository.save(event);

        return toResponseDto(assignment, flight, pad);
    }

    @Override
    public List<PadAssignmentResponseDto> getAllAssignments() {
        return padAssignmentRepository.findAll().stream()
                .map(a-> {
                    Flight f = flightRepository.findById(a.getFlightId()).orElse(null);
                    Pad p = padRepository.findById(a.getPadId()).orElse(null);
                    return toResponseDto(a,f,p);
                }).collect(Collectors.toList());

    }

    @Override
    public PadAssignmentResponseDto getAssignmentById(String id) {
        PadAssignment assignment = padAssignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        Flight f = flightRepository.findById(assignment.getFlightId()).orElse(null);
        Pad p = padRepository.findById(assignment.getPadId()).orElse(null);
        return toResponseDto(assignment, f, p);
    }

    @Override
    public PadAssignmentResponseDto updateAssignment(String id, PadAssignmentRequestDto dto) {
        PadAssignment assignment = padAssignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        assignment.setVertiportId(dto.getVertiportId());
        assignment.setStatus(dto.getStatus());
        padAssignmentRepository.save(assignment);

        Flight f = flightRepository.findById(assignment.getFlightId()).orElse(null);
        Pad p = padRepository.findById(assignment.getPadId()).orElse(null);
        return toResponseDto(assignment, f, p);
    }

    @Override
    public void deleteAssignment(String id) {
        padAssignmentRepository.deleteById(id);
    }

    private PadAssignmentResponseDto toResponseDto(PadAssignment a, Flight f, Pad p){
        PadAssignmentResponseDto dto = new PadAssignmentResponseDto();
        dto.setId(a.getId());
        dto.setFlightId(a.getFlightId());
        dto.setVertiportId(a.getVertiportId());
        dto.setPadId(a.getPadId());
        dto.setStatus(a.getStatus());
        dto.setStartAt(a.getStartAt());
        dto.setEndAt(a.getEndAt());
        return dto;
    }


}
