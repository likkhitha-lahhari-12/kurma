package com.fastflyrr.kurma.app.services.Impl;

import com.fastflyrr.kurma.app.services.EventOutboxService;
import com.fastflyrr.kurma.core.db.repositories.EventOutboxRepository;
import com.fastflyrr.kurma.core.models.EventOutbox;

import java.util.Map;

public class EventOutboxServiceImpl implements EventOutboxService {
    private final EventOutboxRepository eventOutboxRepository;

    public EventOutboxServiceImpl(EventOutboxRepository eventOutboxRepository) {
        this.eventOutboxRepository = eventOutboxRepository;
    }

    @Override
    public void publishEvent(String aggregateType, String aggregateId, String eventType, Map<String, Object> eventData) {
        EventOutbox event = new EventOutbox();
        event.setEventData(eventData);
        event.setEventType(eventType);
        event.setAggregateId(aggregateId);
        event.setAggregateType(aggregateType);
        eventOutboxRepository.save(event);
    }
}
