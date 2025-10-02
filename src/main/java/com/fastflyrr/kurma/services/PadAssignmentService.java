package com.fastflyrr.kurma.services;

import com.fastflyrr.kurma.models.Flight;
import com.fastflyrr.kurma.models.Pad;

public interface PadAssignmentService {

    //to assign an available pad to the given flight
    Pad assignPad(String flightId);

    //released the pad once the flight has departed.
    //so when a flight departs it can hit this endpoint to change the pad's status to free
    //void releasePad(Flight flight);

    //helper check
    //checks if a pad is available for a given flight's vertiport and time.
    //boolean isPadAvailable(Flight flight);

    //scope: add an endpoint to add flights to a waitlist or queue when all pads are occupied.
}
