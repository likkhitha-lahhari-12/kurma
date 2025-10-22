package com.fastflyrr.kurma.core.models;

import com.fastflyrr.kurma.enums.PadStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection =  "pads")
public class Pad {
    @Id
    private String id;
    private String name;
    private PadStatus status;
    private String vertiportId;

}
