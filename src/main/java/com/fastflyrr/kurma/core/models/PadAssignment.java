package com.fastflyrr.kurma.core.models;

import com.fastflyrr.kurma.enums.PadAssignmentStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


@Setter
@Getter
@Document(collection = "pad_assignments")
public class PadAssignment {
    @Id
    private String id;
    private String flightId;
    private String vertiportId;
    private String padId;

    private Instant startAt;
    private Instant endAt;

    private PadAssignmentStatus status; //awarded, confirmed, active, completed, cancelled

}
