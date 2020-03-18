package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.AssessedValueAirplaneDto;
import com.mcb.creditfactory.dto.CollateralWithAssessedValueAirplane;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.model.AssessedValueAirplane;

import java.util.List;
import java.util.Optional;

public interface AirplaneService {
    boolean approve(CollateralWithAssessedValueAirplane collateralWithAssessedValueAirplane); //проверка
    Airplane saveAirplane(Airplane airplane);
    Optional<Airplane> loadAirplane (Long id);
    Airplane fromAirplaneDto(AirplaneDto airplanedto);
    AirplaneDto toAirplaneDto(Airplane airplane);
    Long getId(Airplane airplane);
    Long getIdCollateral (AssessedValueAirplane assessedValueAirplane);
    AirplaneDto toAirplaneDtoOfCollateralWithAssessedValueAirplane (CollateralWithAssessedValueAirplane cwava);
    AssessedValueAirplane saveAssessedValueAirplane (AssessedValueAirplane assessedValueAirplane);
    Optional<List<AssessedValueAirplane>> loadAssessedValueAirplaneList (Long id);
    AssessedValueAirplane loadAssessedValueAirplane (List<AssessedValueAirplane> assessedValueAirplaneList);
    AssessedValueAirplane fromAssessedValueAirplaneDto (AssessedValueAirplaneDto assessedValueAirplaneDto);
    AssessedValueAirplaneDto toAssessedValueAirplaneDto (AssessedValueAirplane assessedValueAirplane);
    AssessedValueAirplaneDto toAssessedValueAirplaneDtoOfCollateralWithAssessedValueAirplane
            (CollateralWithAssessedValueAirplane cwava);
    CollateralWithAssessedValueAirplane toCollateralAssessedValueAirplane
            (AirplaneDto airplaneDto, AssessedValueAirplaneDto assessedValueAirplaneDto);
}
