package com.fastflyrr.kurma.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "gates")
public class Gate {
    @Id
    private String id;
    private String name;
    private String associatedPadId;
    private String associtaedVertiportId;

}
