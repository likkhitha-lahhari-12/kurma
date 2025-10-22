package com.fastflyrr.kurma.app.api.mappers;

import com.fastflyrr.kurma.app.api.dto.FlightRequestDto;
import com.fastflyrr.kurma.app.api.dto.FlightResponseDto;
import com.fastflyrr.kurma.core.models.Flight;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    Flight toEntity(FlightRequestDto dto);
    FlightResponseDto toDto(Flight entity);
}
