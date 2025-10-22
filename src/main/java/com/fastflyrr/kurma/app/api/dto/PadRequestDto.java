package com.fastflyrr.kurma.app.api.dto;

import com.fastflyrr.kurma.enums.PadStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PadRequestDto {

    @NotBlank(message = "Pad name is required")
    private String name;

    @NotNull(message = "Status is required")
    private PadStatus status;

    @NotNull(message = "Vertiport id is required")
    private String vertiportId;


    public @NotBlank(message = "Pad name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Pad name is required") String name) {
        this.name = name;
    }

    public @NotNull(message = "Vertiport id is required") String getVertiportId() {
        return vertiportId;
    }

    public void setVertiportId(@NotNull(message = "Vertiport id is required") String vertiportId) {
        this.vertiportId = vertiportId;
    }

    public @NotNull(message = "Status is required") PadStatus getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "Status is required") PadStatus status) {
        this.status = status;
    }
}
