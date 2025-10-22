package com.fastflyrr.kurma.core.db.repositories;

import com.fastflyrr.kurma.core.models.EventOutbox;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventOutboxRepository extends MongoRepository<EventOutbox, String> {
    List<EventOutbox> findByStatus(String status);
}
