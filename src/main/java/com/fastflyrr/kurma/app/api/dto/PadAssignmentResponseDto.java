package com.fastflyrr.kurma.app.api.dto;

import com.fastflyrr.kurma.enums.PadAssignmentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;


@Setter
@Getter
public class PadAssignmentResponseDto {
    private String id;
    private String flightId;
    private String vertiportId;
    private String padId;

    private Instant startAt;
    private Instant endAt;

    private PadAssignmentStatus status;

}
