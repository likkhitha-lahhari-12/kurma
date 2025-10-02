package com.fastflyrr.kurma.services.Impl;

import com.fastflyrr.kurma.models.Pad;
import com.fastflyrr.kurma.models.Vertiport;
import com.fastflyrr.kurma.repositories.PadRepository;
import com.fastflyrr.kurma.repositories.VertiportRepository;
import com.fastflyrr.kurma.services.PadService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PadServiceImpl implements PadService {

    private final PadRepository padRepository;
    private final VertiportRepository vertiportRepository;

    public PadServiceImpl(PadRepository padRepository, VertiportRepository vertiportRepository) {
        this.padRepository = padRepository;
        this.vertiportRepository = vertiportRepository;
    }

    @Override
    public Pad createPad(Pad pad) {
        Vertiport vertiport = vertiportRepository.findById(pad.getVertiportId())
                .orElseThrow(()-> new RuntimeException("Vertiport not found"));

        //save pad first to get its id
        Pad savedPad = padRepository.save(pad);

        //Add pad to vertiport's pad list
        List<Pad> padsOfVertiport = vertiport.getPads();
        if(padsOfVertiport == null){
            padsOfVertiport = new ArrayList<>();
        }
        padsOfVertiport.add(savedPad);
        vertiport.setPads(padsOfVertiport);

        vertiportRepository.save(vertiport);
        return savedPad;
    }

    @Override
    public List<Pad> getAllPads() {
        return padRepository.findAll();
    }
}
