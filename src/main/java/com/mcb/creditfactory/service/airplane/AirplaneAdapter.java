package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.CollateralWithAssessedValueAirplane;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class AirplaneAdapter implements CollateralObject {

    private CollateralWithAssessedValueAirplane collateralWithAssessedValueAirplane;

    @Override
    public BigDecimal getValue() {
        return collateralWithAssessedValueAirplane.getValue();
    }

    @Override
    public Short getYear() {
        return collateralWithAssessedValueAirplane.getYear();
    }

    @Override
    public LocalDate getDate() { //Здесь необходимо вытащить дату последней оценки из таблицы
        return collateralWithAssessedValueAirplane.getDate();
    }

    @Override
    public CollateralType getType() { return CollateralType.AIRPLANE; }
}
