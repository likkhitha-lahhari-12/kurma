package com.fastflyrr.kurma.app.services.Impl;

import com.fastflyrr.kurma.app.api.dto.VertiportRequestDto;
import com.fastflyrr.kurma.app.api.dto.VertiportResponseDto;
import com.fastflyrr.kurma.app.api.mappers.VertiportMapper;
import com.fastflyrr.kurma.app.services.VertiportService;
import com.fastflyrr.kurma.core.db.repositories.VertiportRepository;
import com.fastflyrr.kurma.core.models.Vertiport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VertiportServiceImpl implements VertiportService {
    private final VertiportRepository vertiportRepository;
    private final VertiportMapper vertiportMapper;

    public VertiportServiceImpl(VertiportRepository vertiportRepository, VertiportMapper vertiportMapper) {
        this.vertiportRepository = vertiportRepository;
        this.vertiportMapper = vertiportMapper;
    }

    @Override
    public VertiportResponseDto createVertiport(VertiportRequestDto dto) {
        Vertiport vertiport = vertiportMapper.toEntity(dto);
        return vertiportMapper.toDto(vertiportRepository.save(vertiport));
    }

    @Override
    public VertiportResponseDto getVertiportById(String id) {
        Vertiport vertiport = vertiportRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Vertiport not found with id: "+id));
        return vertiportMapper.toDto(vertiport);
    }

    @Override
    public List<VertiportResponseDto> getAllVertiports() {
        return vertiportRepository.findAll()
                .stream()
                .map(vertiportMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public VertiportResponseDto updateVertiport(String id, VertiportRequestDto dto) {
        Vertiport vertiport = vertiportRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Vertiport not found with id: "+id));

        vertiport.setName(dto.getName());
        vertiport.setLocation(dto.getLocation());
        vertiport.setTimezone(dto.getTimezone());

        return vertiportMapper.toDto(vertiportRepository.save(vertiport));
    }

    @Override
    public void deleteVertiport(String id) {
        vertiportRepository.deleteById(id);
    }
}
