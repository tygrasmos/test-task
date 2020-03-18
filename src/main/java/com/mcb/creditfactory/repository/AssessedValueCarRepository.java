package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.AssessedValueCar;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AssessedValueCarRepository  extends CrudRepository<AssessedValueCar, Long>{

    Optional<AssessedValueCar> findByIdCollateral(Long id);
}
