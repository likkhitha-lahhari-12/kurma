package com.fastflyrr.kurma.app.services;

import com.fastflyrr.kurma.app.api.dto.VertiportRequestDto;
import com.fastflyrr.kurma.app.api.dto.VertiportResponseDto;
import com.fastflyrr.kurma.core.models.Vertiport;

import java.util.List;

public interface VertiportService {
    VertiportResponseDto createVertiport(VertiportRequestDto dto);
    VertiportResponseDto getVertiportById(String id);
    List<VertiportResponseDto> getAllVertiports();
    VertiportResponseDto updateVertiport(String id, VertiportRequestDto dto);
    void deleteVertiport(String id);
}
