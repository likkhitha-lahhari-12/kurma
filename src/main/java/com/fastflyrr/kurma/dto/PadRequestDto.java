package com.fastflyrr.kurma.dto;

import com.fastflyrr.kurma.enums.PadStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PadRequestDto {

    @NotBlank(message = "Pad name is required")
    private String name;

    @NotNull(message = "Status is required")
    private PadStatus status;

    public @NotBlank(message = "Pad name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Pad name is required") String name) {
        this.name = name;
    }

    public @NotNull(message = "Status is required") PadStatus getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "Status is required") PadStatus status) {
        this.status = status;
    }
}
