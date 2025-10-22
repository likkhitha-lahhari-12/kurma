package com.fastflyrr.kurma.app.api.dto;

import com.fastflyrr.kurma.enums.PadAssignmentStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;


public class PadAssignmentRequestDto {

    @NotBlank(message="Flight id is required")
    private String flightId;

    @NotBlank(message = "Vertiport id is required")
    private String vertiportId;

    @NotBlank(message = "pad Id id is required")
    private String padId;

    @FutureOrPresent(message= "Start time must in in current or future")
    private Instant startAt;
    private Instant endAt;

    private PadAssignmentStatus status;

    public @NotBlank(message = "Flight id is required") String getFlightId() {
        return flightId;
    }

    public void setFlightId(@NotBlank(message = "Flight id is required") String flightId) {
        this.flightId = flightId;
    }

    public @NotBlank(message = "Vertiport id is required") String getVertiportId() {
        return vertiportId;
    }

    public void setVertiportId(@NotBlank(message = "Vertiport id is required") String vertiportId) {
        this.vertiportId = vertiportId;
    }

    public @NotBlank(message = "pad Id id is required") String getPadId() {
        return padId;
    }

    public void setPadId(@NotBlank(message = "pad Id id is required") String padId) {
        this.padId = padId;
    }

    public @FutureOrPresent(message = "Start time must in in current or future") Instant getStartAt() {
        return startAt;
    }

    public void setStartAt(@FutureOrPresent(message = "Start time must in in current or future") Instant startAt) {
        this.startAt = startAt;
    }

    public Instant getEndAt() {
        return endAt;
    }

    public void setEndAt(Instant endAt) {
        this.endAt = endAt;
    }

    public PadAssignmentStatus getStatus() {
        return status;
    }

    public void setStatus(PadAssignmentStatus status) {
        this.status = status;
    }
}
