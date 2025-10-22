package com.fastflyrr.kurma.app.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VertiportRequestDto {
    private String name;
    private String location;
    private String timezone;
}
