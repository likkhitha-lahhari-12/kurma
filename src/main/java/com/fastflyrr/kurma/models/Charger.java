package com.fastflyrr.kurma.models;

import com.fastflyrr.kurma.enums.ChargerStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document(collection= "chargers")
public class Charger {
    @Id
    private String id;
    private String name;
    private String vertiportId;
    private ChargerStatus status;
    private String flightId;

}
