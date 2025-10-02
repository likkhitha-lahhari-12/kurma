package com.fastflyrr.kurma.services;

import com.fastflyrr.kurma.models.Pad;

import java.util.List;

public interface PadService {
     Pad createPad(Pad pad);
     List<Pad> getAllPads();
}
