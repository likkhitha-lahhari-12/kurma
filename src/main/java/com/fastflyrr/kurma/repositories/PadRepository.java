package com.fastflyrr.kurma.repositories;

import com.fastflyrr.kurma.enums.PadStatus;
import com.fastflyrr.kurma.models.Pad;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PadRepository extends MongoRepository<Pad, String> {
    //find the first pad my given status
    Optional<Pad> findFirstByStatus(PadStatus status);

    //get all pads with a given status
    List<Pad> findAllByStatus(PadStatus status);

    //check if any pad is available
    boolean existsByStatus(PadStatus status);



    Optional<Pad> findFirstByVertiportIdAndStatus(String vertiportId, PadStatus status);


}
