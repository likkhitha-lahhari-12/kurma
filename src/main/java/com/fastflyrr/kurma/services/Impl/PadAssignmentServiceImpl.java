package com.fastflyrr.kurma.services.Impl;

import com.fastflyrr.kurma.enums.PadStatus;
import com.fastflyrr.kurma.models.Flight;
import com.fastflyrr.kurma.models.Pad;
import com.fastflyrr.kurma.repositories.FlightRepository;
import com.fastflyrr.kurma.repositories.PadRepository;
import com.fastflyrr.kurma.services.PadAssignmentService;
import org.springframework.stereotype.Service;

@Service
public class PadAssignmentServiceImpl implements PadAssignmentService {

    private final PadRepository padRepository;
    private final FlightRepository flightRepository;

    public PadAssignmentServiceImpl(PadRepository padRepository, FlightRepository flightRepository) {
        this.padRepository = padRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public Pad assignPad(String flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(()-> new RuntimeException("Flight not found: "+flightId));
        Pad pad=  padRepository.findFirstByVertiportIdAndStatus(
                flight.getVertiportId(),
                PadStatus.FREE
        ).orElseThrow(()-> new RuntimeException("no free pad available at this vertiport"));

        pad.setStatus(PadStatus.OCCUPIED);
        pad.setFlightId(flight.getId());

        flight.setPadId(pad.getId());

        padRepository.save(pad);
        flightRepository.save(flight);

        return pad;
    }

}
