package com.fastflyrr.kurma.dto;

import com.fastflyrr.kurma.enums.PadStatus;
import lombok.Data;

@Data
public class PadResponseDto {
        private String id;
        private String name;
        private PadStatus status;
        private String flightId;

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

        public PadStatus getStatus() {
                return status;
        }

        public void setStatus(PadStatus status) {
                this.status = status;
        }

        public String getFlightId() {
                return flightId;
        }

        public void setFlightId(String flightId) {
                this.flightId = flightId;
        }
}
