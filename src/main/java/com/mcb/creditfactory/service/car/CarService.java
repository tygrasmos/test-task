package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.AssessedValueCarDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.CollateralWithAssessedValueCar;
import com.mcb.creditfactory.model.AssessedValueCar;
import com.mcb.creditfactory.model.Car;

import java.util.Optional;

public interface CarService {
    boolean approve(CollateralWithAssessedValueCar collateralWithAssessedValueCar);
    Car saveCar(Car car);
    AssessedValueCar saveAssessedValueCar (AssessedValueCar assessedValueCar);
    Optional<Car> loadCar(Long id);
    Optional<AssessedValueCar> loadAssessedValueCar (Long id);
    Car fromCarDto(CarDto cardto);
    AssessedValueCar fromAssessedValueCarDto (AssessedValueCarDto assessedValueCarDto);
    CarDto toCarDto(Car car);
    AssessedValueCarDto toAssessedValueCarDto (AssessedValueCar assessedValueCar);
    Long getId(Car car);
    Long getIdCollateral(AssessedValueCar assessedValueCar);
    CarDto toCarDtoOfCollateralWithAssessedValueCar
            (CollateralWithAssessedValueCar collateralWithAssessedValueCar);
    AssessedValueCarDto toAssessedValueCarDtoOfCollateralWithAssessedValueCar
            (CollateralWithAssessedValueCar collateralWithAssessedValueCar);
    CollateralWithAssessedValueCar toCollateralAssessedValueCar
            (CarDto carDto, AssessedValueCarDto assessedValueCarDto);
}
