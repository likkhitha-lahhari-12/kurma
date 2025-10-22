package com.fastflyrr.kurma.app.services;

import com.fastflyrr.kurma.app.api.dto.PadRequestDto;
import com.fastflyrr.kurma.app.api.dto.PadResponseDto;
import com.fastflyrr.kurma.core.models.Pad;

import java.util.List;

public interface PadService {
     PadResponseDto createPad(PadRequestDto pad);
     List<PadResponseDto> getAllPads();
     PadResponseDto getPadById(String id);
     PadResponseDto updatePad(String id, PadRequestDto request);
     void deletePad(String id);
}
