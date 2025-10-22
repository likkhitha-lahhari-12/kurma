package com.fastflyrr.kurma.core.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "vertiports")
public class Vertiport {

    @Id
    private String id;
    private String name;
    private String location;
    private String timezone = "UTC";

}
