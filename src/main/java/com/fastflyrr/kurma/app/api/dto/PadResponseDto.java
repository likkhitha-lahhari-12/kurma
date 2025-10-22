package com.fastflyrr.kurma.app.api.dto;

import com.fastflyrr.kurma.enums.PadStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class PadResponseDto {
        private String id;
        private String name;
        private PadStatus status;
        private String vertiportId;

}
