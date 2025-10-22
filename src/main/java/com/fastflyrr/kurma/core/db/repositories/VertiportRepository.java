package com.fastflyrr.kurma.core.db.repositories;

import com.fastflyrr.kurma.core.models.Vertiport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VertiportRepository extends MongoRepository<Vertiport, String> {

}
