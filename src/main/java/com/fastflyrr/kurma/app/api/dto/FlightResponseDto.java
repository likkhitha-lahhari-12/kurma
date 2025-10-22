package com.fastflyrr.kurma.app.api.dto;

import com.fastflyrr.kurma.enums.FlightStatus;
import com.fastflyrr.kurma.enums.Priority;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
public class FlightResponseDto {
    private String id;
    private String flightNumber;
    private Priority priority;
    private FlightStatus status;

}
