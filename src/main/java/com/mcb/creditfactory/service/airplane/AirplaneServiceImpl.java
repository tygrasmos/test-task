package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.AssessedValueAirplaneDto;
import com.mcb.creditfactory.dto.CollateralWithAssessedValueAirplane;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.model.AssessedValueAirplane;
import com.mcb.creditfactory.repository.AirplaneRepository;
import com.mcb.creditfactory.repository.AssessedValueAirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneServiceImpl implements AirplaneService{

    @Autowired
    private ExternalApproveService approveService;

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private AssessedValueAirplaneRepository assessedValueAirplaneRepository;

    @Override // Проверить обеспечение на минимальные требования
    public boolean approve(CollateralWithAssessedValueAirplane collateralWithAssessedValueAirplane) {
        return approveService.approve(new AirplaneAdapter(collateralWithAssessedValueAirplane)) == 0;;
    }

    @Override // Сохранение (обновление) в базу данных сущности "обеспечение самолет"
    public Airplane saveAirplane(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    @Override // Получение сущности "обеспечение самолет"
    public Optional<Airplane> loadAirplane(Long id) { return airplaneRepository.findById(id); }

    @Override // Получить из отображения "обеспечение самолет" сущность "обеспечение самолет"
    public Airplane fromAirplaneDto(AirplaneDto airplanedto) {
        return new Airplane(
                airplanedto.getId(),
                airplanedto.getBrand(),
                airplanedto.getModel(),
                airplanedto.getManufacturer(),
                airplanedto.getYear(),
                airplanedto.getFuelCapacity(),
                airplanedto.getSeats()
        );
    }

    @Override // Получить из сущности "обеспечение самолет" отображения "обеспечение самолет"
    public AirplaneDto toAirplaneDto(Airplane airplane) {
        return new AirplaneDto(
                airplane.getId(),
                airplane.getBrand(),
                airplane.getModel(),
                airplane.getManufacturer(),
                airplane.getYear(),
                airplane.getFuelCapacity(),
                airplane.getSeats()
        );
    }

    @Override // Получение значения id заданной сущности "обеспечение самолет"
    public Long getId(Airplane airplane) {
        return airplane.getId();
    }

    @Override // Получение значения id обеспечения из сущности "оценочная стоимость обеспечения самолет"
    public Long getIdCollateral(AssessedValueAirplane assessedValueAirplane) {
        return assessedValueAirplane.getIdCollateral();
    }

    @Override // Получение отображения "обеспечение самолет" из отображения "обеспечение самолет с оценочной стоимостью"
    public AirplaneDto toAirplaneDtoOfCollateralWithAssessedValueAirplane(CollateralWithAssessedValueAirplane cwava) {
        return new AirplaneDto(
                airplane.getId(),
                airplane.getBrand(),
                airplane.getModel(),
                airplane.getManufacturer(),
                airplane.getYear(),
                airplane.getFuelCapacity(),
                airplane.getSeats()
        );
    }

    @Override // Сохранение (обновление) в базу данных сущности "оценочная стоимость обеспечения самолет"
    public AssessedValueAirplane saveAssessedValueAirplane(AssessedValueAirplane assessedValueAirplane) {
        return assessedValueAirplaneRepository.save(assessedValueAirplane);
    }

    @Override // Получение списка записей сущности "оценочная стоимость самолет"
    public Optional<List<AssessedValueAirplane>> loadAssessedValueAirplaneList(Long id) {
        return assessedValueAirplaneRepository.findByIdCollateral(id);
    }

    @Override // Получение записи о последней оценке "обеспечение самолет"
    public AssessedValueAirplane loadAssessedValueAirplane(List<AssessedValueAirplane> assessedValueAirplaneList) {
        return assessedValueAirplaneList.get(assessedValueAirplaneList.size());
    }

    @Override // Получение сущности "оценочная стоимость самолет" из отображения "оценочная стоимость обеспечения самолет"
    public AssessedValueAirplane fromAssessedValueAirplaneDto(AssessedValueAirplaneDto assessedValueAirplaneDto) {
        return new AssessedValueAirplane(
                assessedValueAirplaneDto.getId(),
                assessedValueAirplaneDto.getIdCollateral(),
                assessedValueAirplaneDto.getDate(),
                assessedValueAirplaneDto.getValue()
        );
    }

    @Override // Получение отображения "оценочная стоимость самолет" из сущности "оценочная стоимость обеспечения самолет"
    public AssessedValueAirplaneDto toAssessedValueAirplaneDto(AssessedValueAirplane assessedValueAirplane) {
        return new AssessedValueAirplane(
                assessedValueAirplane.getId(),
                assessedValueAirplane.getIdCollateral(),
                assessedValueAirplane.getDate(),
                assessedValueAirplane.getValue()
        );
    }

    @Override // Получение отображения "оценочная стоимость самолет" из отображения "обеспечение самолет с оценочной стоимостью"
    public AssessedValueAirplaneDto toAssessedValueAirplaneDtoOfCollateralWithAssessedValueAirplane
            (CollateralWithAssessedValueAirplane cwava) {
        return new AssessedValueAirplaneDto(
                null,
                cwava.getId(),
                cwava.getDate(),
                cwava.getValue()
        );
    }

    @Override // Получение отображения "обеспечение самолет с оценочной стоимостью" из отображений
              // "обеспечение самолет" и "оценочная стоимость обеспечения самолет"
    public CollateralWithAssessedValueAirplane toCollateralAssessedValueAirplane
            (AirplaneDto airplaneDto, AssessedValueAirplaneDto assessedValueAirplaneDto) {
        return new CollateralWithAssessedValueAirplane(
                airplaneDto.getId(),
                airplaneDto.getBrand(),
                airplaneDto.getModel(),
                airplaneDto.getManufacturer(),
                airplaneDto.getYear(),
                airplaneDto.getFuelCapacity(),
                airplaneDto.getSeats(),
                assessedValueAirplaneDto.getDate(),
                assessedValueAirplaneDto.getValue()
        );
    }
}
