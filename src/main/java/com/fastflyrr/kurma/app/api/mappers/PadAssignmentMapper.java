package com.fastflyrr.kurma.app.api.mappers;

import com.fastflyrr.kurma.app.api.dto.PadAssignmentRequestDto;
import com.fastflyrr.kurma.app.api.dto.PadAssignmentResponseDto;
import com.fastflyrr.kurma.core.models.PadAssignment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PadAssignmentMapper {
    PadAssignment toEntity(PadAssignmentRequestDto Dto);
    PadAssignmentResponseDto toDto(PadAssignment entity);
}
