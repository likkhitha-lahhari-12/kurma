package com.fastflyrr.kurma.mappers;

import com.fastflyrr.kurma.dto.PadRequestDto;
import com.fastflyrr.kurma.dto.PadResponseDto;
import com.fastflyrr.kurma.models.Pad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PadMapper {
    Pad toEntity(PadRequestDto dto);
    PadResponseDto toDto(Pad entity);
}


