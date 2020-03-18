package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.*;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import com.mcb.creditfactory.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// TODO: reimplement this
@Service
public class CollateralService {
    @Autowired
    private CarService carService;
    @Autowired
    private AirplaneService airplaneService;

    @SuppressWarnings("ConstantConditions")
  /*  public Long saveCollateral(Collateral object) {
        if (!(object instanceof CarDto)) { //проверяем пренадлежит ли объект семейству классов CarDto, если да то дальше
            throw new IllegalArgumentException();
        }

        CarDto car = (CarDto) object; //Приводм объект object к классу CarDto
        boolean approved = carService.approve(car); // Проверяем если оценка true, то идем дальше.
        if (!approved) {                     // Если false (это значит что один из минимальных критериев оценки не выполнен)
            return null;
        }

        return Optional.of(car)
                .map(carService::fromCarDto)
                .map(carService::saveCar)
                .map(carService::getId)
                .orElse(null); //Строка по умолчанию
    } //.map(carService::fromDto) - получаем экземпляр класса Car из класса CarDto
      //.map(carService::save) - сохраняем экземпляр класса Car в репозитарий
      //.map(carService::getId) - получаем значение поля Id*/

    //*************** Сохраняет (обновляет) объект обеспечения проверяя на мин требования оценки **********************//

    public Long saveCollateral(Collateral object) {
        if (!(object instanceof CollateralWithAssessedValueAirplane) & !(object instanceof CollateralWithAssessedValueCar)) {
            throw new IllegalArgumentException();
        }

        if (object instanceof CollateralWithAssessedValueAirplane) {

            boolean approved = airplaneService.approve((CollateralWithAssessedValueAirplane) object);

              if (!approved) {                     // Если false (это значит что один из минимальных критериев оценки не выполнен)
                return null;
              }
            Optional.of((CollateralWithAssessedValueAirplane) object)
                    .map(airplaneService::toAssessedValueAirplaneDtoOfCollateralWithAssessedValueAirplane)
                    .map(airplaneService::fromAssessedValueAirplaneDto)
                    .map(airplaneService::saveAssessedValueAirplane)
                    .orElse(null);

            return Optional.of((CollateralWithAssessedValueAirplane) object)
                    .map(airplaneService::toAirplaneDtoOfCollateralWithAssessedValueAirplane)
                    .map(airplaneService::fromAirplaneDto)
                    .map(airplaneService::saveAirplane)
                    .map(airplaneService::getId)
                    .orElse(null)
                    ;
        } else if (object instanceof CollateralWithAssessedValueCar){

            boolean approved = carService.approve((CollateralWithAssessedValueCar) object);
            if (!approved) {                     // Если false (это значит что один из минимальных критериев оценки не выполнен)
                return null;
            }
            Optional.of((CollateralWithAssessedValueCar) object)
                    .map(carService::toAssessedValueCarDtoOfCollateralWithAssessedValueCar)
                    .map(carService::fromAssessedValueCarDto)
                    .map(carService::saveAssessedValueCar)
                    .orElse(null);

            return Optional.of((CollateralWithAssessedValueCar) object)
                    .map(carService::toCarDtoOfCollateralWithAssessedValueCar)
                    .map(carService::fromCarDto)
                    .map(carService::saveCar)
                    .map(carService::getId)
                    .orElse(null)
                    ;
        } else
       return null;
    }

    //*****************************************************************************************************************//

  /*  public Long saveCollateral3 (Collateral object){
        if (!(object instanceof CollateralWithAssessedValueCar)) {
            throw new IllegalArgumentException();
        } else {

            boolean approved = carService.approve((CollateralWithAssessedValueCar) object);
            if (!approved) {                     // Если false (это значит что один из минимальных критериев оценки не выполнен)
                return null;
            }
            Optional.of((CollateralWithAssessedValueCar) object)
                    .map(carService::toAssessedValueCarDtoOfCollateralWithAssessedValueCar)
                    .map(carService::fromAssessedValueCarDto)
                    .map(carService::saveAssessedValueCar)
                    .orElse(null);

            return Optional.of((CollateralWithAssessedValueCar) object)
                    .map(carService::toCarDtoOfCollateralWithAssessedValueCar)
                    .map(carService::fromCarDto)
                    .map(carService::saveCar)
                    .map(carService::getId)
                    .orElse(null)
                    ;
        }
    }*/

  /*  public Collateral getInfo(Collateral object) {
        if (!(object instanceof CarDto)) {
            throw new IllegalArgumentException();
        }

        return Optional.of((CarDto) object)
                .map(carService::fromDto)
                .map(carService::getId)
                .flatMap(carService::load)
                .map(carService::toDTO)
                .orElse(null);
    } //.map(carService::fromDto) - получаем экземпляр класса Car из класса CarDto
      //.map(carService::getId) - получаем значение поля Id
      //.flatMap(carService::load) - получаем по Id из репозитария запись соответствующую этому Id
      //.map(carService::toDTO) - получаем экземпляр класса CarDto из класса Car*/

    //*************** Возвращает запись обеспечение и его (последняя) оценка ***********************************//

    public Collateral getInfo (Collateral object){ //Возвращает запись обеспечение(самолет) и его последняя оценка
        if ((!(object instanceof CollateralWithAssessedValueAirplane)) & !(object instanceof CollateralWithAssessedValueCar)) {
            throw new IllegalArgumentException();
        }
        if (object instanceof CollateralWithAssessedValueAirplane) {
            AirplaneDto airplaneDto = Optional.of((AirplaneDto) object)
                    .map(airplaneService::fromAirplaneDto)
                    .map(airplaneService::getId)
                    .flatMap(airplaneService::loadAirplane)
                    .map(airplaneService::toAirplaneDto)
                    .orElse(null);

            AssessedValueAirplaneDto assessedValueAirplaneDto = Optional.of((AirplaneDto) object)
                    .map(airplaneService::fromAirplaneDto)
                    .map(airplaneService::getId)
                    .flatMap(airplaneService::loadAssessedValueAirplaneList)
                    .map(airplaneService::loadAssessedValueAirplane)
                    .map(airplaneService::toAssessedValueAirplaneDto)
                    .orElse(null);

            return airplaneService.toCollateralAssessedValueAirplane(airplaneDto, assessedValueAirplaneDto);
        }
        else if (object instanceof CollateralWithAssessedValueCar){
            CarDto carDto = Optional.of((CarDto) object)
                    .map(carService::fromCarDto)
                    .map(carService::getId)
                    .flatMap(carService::loadCar)
                    .map(carService::toCarDto)
                    .orElse(null);

            AssessedValueCarDto assessedValueCarDto = Optional.of((CarDto) object)
                    .map(carService::fromCarDto)
                    .map(carService::getId)
                    .flatMap(carService::loadAssessedValueCar)
                    .map(carService::toAssessedValueCarDto)
                    .orElse(null);

            return carService.toCollateralAssessedValueCar(carDto, assessedValueCarDto);
        }
        else return null;
    }

    //*****************************************************************************************************************//

}
