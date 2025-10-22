package com.fastflyrr.kurma.core.db.repositories;

import com.fastflyrr.kurma.core.models.PadAssignment;
import com.fastflyrr.kurma.enums.PadAssignmentStatus;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PadAssignmentRepository extends MongoRepository<PadAssignment, String> {

    boolean existsByFlightIdAndStatusIn(@NotBlank(message = "Flight id is required") String flightId, List<PadAssignmentStatus> awarded);
}
