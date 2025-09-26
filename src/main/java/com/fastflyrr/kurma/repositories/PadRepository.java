package com.fastflyrr.kurma.repositories;

import com.fastflyrr.kurma.enums.PadStatus;
import com.fastflyrr.kurma.models.Pad;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PadRepository extends MongoRepository<Pad, String> {
    Optional<Pad> findFirstByStatus(PadStatus status);
}
