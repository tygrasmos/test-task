package com.mcb.creditfactory.dto;


import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("collateralwithAVAirplane")
public class CollateralWithAssessedValueAirplane implements Collateral {
    private Long id;
    private String brand;
    private String model;
    private String manufacturer;
    private Short year;
    private int fuelCapacity;
    private int seats;
    private LocalDate date;
    private BigDecimal value;
}
