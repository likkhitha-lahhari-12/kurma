package com.fastflyrr.kurma.dto;

import com.fastflyrr.kurma.enums.Priority;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightResponseDto {
    private String id;
    private String flightNumber;
    private LocalDateTime eta;
    private Priority priority;
    private String padId;
}
