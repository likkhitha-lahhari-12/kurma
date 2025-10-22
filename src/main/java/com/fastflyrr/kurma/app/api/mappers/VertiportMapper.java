package com.fastflyrr.kurma.app.api.mappers;

import com.fastflyrr.kurma.app.api.dto.VertiportRequestDto;
import com.fastflyrr.kurma.app.api.dto.VertiportResponseDto;
import com.fastflyrr.kurma.core.models.Vertiport;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VertiportMapper {
     Vertiport toEntity(VertiportRequestDto dto);
     VertiportResponseDto toDto(Vertiport entity);
}
