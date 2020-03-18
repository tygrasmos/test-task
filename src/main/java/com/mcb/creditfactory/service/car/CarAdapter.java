package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.CollateralWithAssessedValueCar;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class CarAdapter implements CollateralObject {
    private CollateralWithAssessedValueCar collateralWithAssessedValueCar;

    @Override
    public BigDecimal getValue() {
        return collateralWithAssessedValueCar.getValue();
    }

    @Override
    public Short getYear() {
        return collateralWithAssessedValueCar.getYear();
    }

    @Override
    public LocalDate getDate() {
        // Для автомобилей дата оценки не используется, поэтому всегда берем текущую
        return LocalDate.now();
    }

    @Override
    public CollateralType getType() {
        return CollateralType.CAR;
    }
}
