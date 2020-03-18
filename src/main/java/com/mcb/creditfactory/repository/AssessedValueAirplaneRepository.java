package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.AssessedValueAirplane;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AssessedValueAirplaneRepository extends CrudRepository<AssessedValueAirplane,Long> {

   Optional<List<AssessedValueAirplane>> findByIdCollateral(Long id);

}
