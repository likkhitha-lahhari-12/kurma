package com.fastflyrr.kurma.mappers;

import com.fastflyrr.kurma.dto.FlightRequestDto;
import com.fastflyrr.kurma.dto.FlightResponseDto;
import com.fastflyrr.kurma.models.Flight;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    Flight toEntity(FlightRequestDto dto);
    FlightResponseDto toDto(Flight entity);

}
