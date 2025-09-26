package com.fastflyrr.kurma.services.Impl;

import com.fastflyrr.kurma.enums.PadStatus;
import com.fastflyrr.kurma.models.Flight;
import com.fastflyrr.kurma.models.Pad;
import com.fastflyrr.kurma.repositories.FlightRepository;
import com.fastflyrr.kurma.repositories.PadRepository;
import com.fastflyrr.kurma.services.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final PadRepository padRepository;

    public FlightServiceImpl(FlightRepository flightRepository, PadRepository padRepository) {
        this.flightRepository = flightRepository;
        this.padRepository = padRepository;
    }

    @Override
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight assignPad(String flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found: " + flightId));

        Pad pad = padRepository.findFirstByStatus(PadStatus.FREE)
                .orElseThrow(() -> new RuntimeException("No available pads!"));

        pad.setStatus(PadStatus.OCCUPIED);
        pad.setFlightId(flight.getId());
        padRepository.save(pad);

        flight.setPadId(pad.getId());
        return flightRepository.save(flight);
    }
}
