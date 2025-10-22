package com.fastflyrr.kurma.app.services;

import com.fastflyrr.kurma.app.api.dto.PadAssignmentRequestDto;
import com.fastflyrr.kurma.app.api.dto.PadAssignmentResponseDto;
import com.fastflyrr.kurma.core.models.Pad;
import com.fastflyrr.kurma.core.models.PadAssignment;

import java.util.List;

public interface PadAssignmentService {

    void completeAssignment(String assignmentId);
    PadAssignmentResponseDto assignPad(PadAssignmentRequestDto dto);
    List<PadAssignmentResponseDto> getAllAssignments();
    PadAssignmentResponseDto getAssignmentById(String id);
    PadAssignmentResponseDto updateAssignment(String id, PadAssignmentRequestDto dto);
    void deleteAssignment(String id);


}
