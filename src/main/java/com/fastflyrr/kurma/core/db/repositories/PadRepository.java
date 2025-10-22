package com.fastflyrr.kurma.core.db.repositories;

import com.fastflyrr.kurma.enums.PadStatus;
import com.fastflyrr.kurma.core.models.Pad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PadRepository extends MongoRepository<Pad, String> {
    //find the first pad my given status
    Optional<Pad> findFirstByStatus(PadStatus status);

    //to list all pads for a vertiport
    List<Pad> findAllByVertiportId(String vertiportId);

    //get all pads with a given status
    List<Pad> findAllByStatus(PadStatus status);

    //check if any pad is available
    boolean existsByStatus(PadStatus status);



    Optional<Pad> findFirstByVertiportIdAndStatus(String vertiportId, PadStatus status);


}
