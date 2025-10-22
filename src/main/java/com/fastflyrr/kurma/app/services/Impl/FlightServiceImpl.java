package com.fastflyrr.kurma.app.services.Impl;

import com.fastflyrr.kurma.app.api.dto.FlightRequestDto;
import com.fastflyrr.kurma.app.api.dto.FlightResponseDto;
import com.fastflyrr.kurma.app.api.mappers.FlightMapper;
import com.fastflyrr.kurma.core.models.Flight;
import com.fastflyrr.kurma.core.db.repositories.FlightRepository;
import com.fastflyrr.kurma.app.services.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightServiceImpl(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @Override
    public FlightResponseDto createFlight(FlightRequestDto request) {
        Flight flight = flightMapper.toEntity(request);
        Flight savedFlight = flightRepository.save(flight);
        return flightMapper.toDto(savedFlight);
    }

    @Override
    public List<FlightResponseDto> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FlightResponseDto getFlightById(String id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Flight not found with id: "+id));
        return flightMapper.toDto(flight);
    }

    @Override
    public FlightResponseDto updateFlight(String id, FlightRequestDto request) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Flight not found with id: "+id));

        flight.setFlightNumber(request.getFlightNumber());
        flight.setStatus(request.getStatus());
        flight.setPriority(request.getPriority());

        Flight updated = flightRepository.save(flight);
        return flightMapper.toDto(updated);
    }

    @Override
    public void deleteFlight(String id) {
        if(!flightRepository.existsById(id)){
            throw new RuntimeException("Flight not found with id: "+id);
        }
        flightRepository.deleteById(id);
    }

}
