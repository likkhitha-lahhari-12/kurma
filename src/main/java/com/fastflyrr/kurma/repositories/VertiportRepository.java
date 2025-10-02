package com.fastflyrr.kurma.repositories;

import com.fastflyrr.kurma.models.Vertiport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VertiportRepository extends MongoRepository<Vertiport, String> {

}
