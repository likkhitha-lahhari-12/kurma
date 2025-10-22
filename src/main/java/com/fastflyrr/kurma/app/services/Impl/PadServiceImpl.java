package com.fastflyrr.kurma.app.services.Impl;

import com.fastflyrr.kurma.app.api.dto.PadRequestDto;
import com.fastflyrr.kurma.app.api.dto.PadResponseDto;
import com.fastflyrr.kurma.app.api.mappers.PadMapper;
import com.fastflyrr.kurma.core.models.Pad;
import com.fastflyrr.kurma.core.models.Vertiport;
import com.fastflyrr.kurma.core.db.repositories.PadRepository;
import com.fastflyrr.kurma.core.db.repositories.VertiportRepository;
import com.fastflyrr.kurma.app.services.PadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PadServiceImpl implements PadService {

    private final PadRepository padRepository;
    private final PadMapper padMapper;

    public PadServiceImpl(PadRepository padRepository, PadMapper padMapper) {
        this.padRepository = padRepository;
        this.padMapper = padMapper;
    }

    @Override
    public PadResponseDto createPad(PadRequestDto request) {
        Pad pad = padMapper.toEntity(request);
        Pad saved = padRepository.save(pad);
        return padMapper.toDto(saved);
    }

    @Override
    public List<PadResponseDto> getAllPads() {
        return padRepository.findAll()
                .stream()
                .map(padMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PadResponseDto getPadById(String id) {
        Pad pad = padRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Pad not found with id: "+id));
        return padMapper.toDto(pad);
    }

    @Override
    public PadResponseDto updatePad(String id, PadRequestDto request) {
        Pad pad = padRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Pad not found with id: "+id));

        pad.setName(request.getName());
        pad.setStatus(request.getStatus());
        pad.setVertiportId(request.getVertiportId());

        Pad updated = padRepository.save(pad);
        return padMapper.toDto(updated);
    }

    @Override
    public void deletePad(String id) {
        if(!padRepository.existsById(id)){
            throw new RuntimeException("Pad not found with id: "+id);
        }
        padRepository.deleteById(id);
    }
}
