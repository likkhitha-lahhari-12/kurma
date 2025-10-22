package com.fastflyrr.kurma.app.services;

import java.util.Map;

public interface EventOutboxService {
    void publishEvent(String aggregateType, String aggregateId, String eventType, Map<String, Object> eventData);
}
