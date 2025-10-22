package com.fastflyrr.kurma.app.api.dto;

import com.fastflyrr.kurma.enums.FlightStatus;
import com.fastflyrr.kurma.enums.Priority;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class FlightRequestDto {

    @NotBlank(message="Flight number is required")
    private String flightNumber;

    @NotNull(message = "Priority is required")
    private Priority priority;

    @Setter
    @Getter
    private FlightStatus status;

    public @NotBlank(message = "Flight number is required")
    String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(@NotBlank(message = "Flight number is required") String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public @NotNull(message = "Priority is required")
    Priority getPriority() {
        return priority;
    }

    public void setPriority(@NotNull(message = "Priority is required") Priority priority) {
        this.priority = priority;
    }
}
