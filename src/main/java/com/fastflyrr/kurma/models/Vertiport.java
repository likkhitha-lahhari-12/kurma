package com.fastflyrr.kurma.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "vertiports")
public class Vertiport {

    @Id
    private String id;
    private String name;
    private String location;
    //private List<Pad> pads;
   // private List<Gate> gates;
   // private List<Charger> chargers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



//    public List<Gate> getGates() {
//        return gates;
//    }
//
//    public void setGates(List<Gate> gates) {
//        this.gates = gates;
//    }
//
//    public List<Charger> getChargers() {
//        return chargers;
//    }
//
//    public void setChargers(List<Charger> chargers) {
//        this.chargers = chargers;
//    }
}
