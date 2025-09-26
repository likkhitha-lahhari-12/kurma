package com.fastflyrr.kurma.dto;

import com.fastflyrr.kurma.enums.Priority;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class FlightRequestDto {

    @NotBlank(message="Flight number is required")
    private String flightNumber;

    @NotNull(message="ETA is required")
    @Future(message="ETA must be in the future")
    private LocalDateTime eta;

    @NotNull(message = "Priority is required")
    private Priority priority;

    public @NotBlank(message = "Flight number is required") String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(@NotBlank(message = "Flight number is required") String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public @NotNull(message = "ETA is required") @Future(message = "ETA must be in the future") LocalDateTime getEta() {
        return eta;
    }

    public void setEta(@NotNull(message = "ETA is required") @Future(message = "ETA must be in the future") LocalDateTime eta) {
        this.eta = eta;
    }

    public @NotNull(message = "Priority is required") Priority getPriority() {
        return priority;
    }

    public void setPriority(@NotNull(message = "Priority is required") Priority priority) {
        this.priority = priority;
    }
}
