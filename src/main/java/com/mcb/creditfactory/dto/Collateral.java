package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CarDto.class),
        @JsonSubTypes.Type(value = AssessedValueCarDto.class),
        @JsonSubTypes.Type(value = AirplaneDto.class),
        @JsonSubTypes.Type(value = AssessedValueAirplaneDto.class),
        @JsonSubTypes.Type(value = CollateralWithAssessedValueAirplane.class),
        @JsonSubTypes.Type(value = CollateralWithAssessedValueCar.class)
})
public interface Collateral {
}
