package com.fastflyrr.kurma.app.api.dto;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VertiportResponseDto {
    private String id;
    private String name;
    private String location;
    private String timezone;

}
