package com.fastflyrr.kurma.core.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Setter
@Getter
@Document(collection = "event_outbox")
public class EventOutbox {
    @Id
    private String Id;

    private String aggregateType; //ex: PadAssignment
    private String aggregateId; //ex: padAssignmentId
    private String eventType; //ex: kurma.pad.assigned
    private Map<String, Object> eventData;
    private String status = "NEW"; //new, processed, failed
    private Instant createdAt = Instant.now();
    private Instant publishedAt;
    private Integer retries = 0;

}
