package com.fastflyrr.kurma.app.services;

import com.fastflyrr.kurma.app.api.dto.FlightRequestDto;
import com.fastflyrr.kurma.app.api.dto.FlightResponseDto;
import com.fastflyrr.kurma.core.models.Flight;

import java.util.List;

public interface FlightService {
    FlightResponseDto createFlight(FlightRequestDto request);
    List<FlightResponseDto> getAllFlights();
    FlightResponseDto getFlightById(String id);
    FlightResponseDto updateFlight(String id, FlightRequestDto request);
    void deleteFlight(String id);

    //String markFlightLanded(String flightId);
    //String markFlightDeparted(String flightId);
}
