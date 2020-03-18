package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("assessedValueCar")
public class AssessedValueCarDto implements Collateral{
    private Long id;
    private Long idCollateral;
    private BigDecimal value;
}
