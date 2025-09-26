package com.fastflyrr.kurma.services;

import com.fastflyrr.kurma.models.Flight;

import java.util.List;

public interface FlightService {
    Flight createFlight(Flight flight);
    List<Flight> getAllFlights();
    Flight assignPad(String flightId);
}
