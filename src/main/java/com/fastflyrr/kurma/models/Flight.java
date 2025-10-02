package com.fastflyrr.kurma.models;

import com.fastflyrr.kurma.enums.FlightStatus;
import com.fastflyrr.kurma.enums.Priority;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document(collection = "flights")
public class Flight {
    @Id
    private String id;
    private String flightNumber;
    private LocalDateTime eta;
    private Priority priority;
    private String padId;
    private FlightStatus status;
    private String vertiportId;




}
