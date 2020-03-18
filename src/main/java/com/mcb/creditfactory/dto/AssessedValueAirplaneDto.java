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
@JsonTypeName("assessedValueAirplane")
public class AssessedValueAirplaneDto implements Collateral{
    private Long id;
    private Long idCollateral;
    private LocalDate date;
    private BigDecimal value;
}
