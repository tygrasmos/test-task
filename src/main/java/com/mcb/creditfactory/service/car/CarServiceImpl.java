package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.AssessedValueCarDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.CollateralWithAssessedValueCar;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.AssessedValueCar;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.repository.AssessedValueCarRepository;
import com.mcb.creditfactory.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    @Autowired //Автоматическое подставление класса без его инициализации. Все делается само
    private ExternalApproveService approveService; //

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private AssessedValueCarRepository assessedValueCarRepository;

    @Override
    public boolean approve(CollateralWithAssessedValueCar collateralWithAssessedValueCar) {
        return approveService.approve(new CarAdapter(collateralWithAssessedValueCar)) == 0;
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public AssessedValueCar saveAssessedValueCar(AssessedValueCar assessedValueCar) {
        return null;
    }

    @Override
    public Optional<Car> loadCar(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Optional<AssessedValueCar> loadAssessedValueCar(Long id) {
        return assessedValueCarRepository.findByIdCollateral(id);
    }

    @Override
    public Car fromCarDto(CarDto cardto) {
        return new Car(
                cardto.getId(),
                cardto.getBrand(),
                cardto.getModel(),
                cardto.getPower(),
                cardto.getYear()
        );
    }

    @Override
    public AssessedValueCar fromAssessedValueCarDto(AssessedValueCarDto assessedValueCarDto) {
        return null;
    }

    @Override
    public CarDto toCarDto(Car car) {
        return new CarDto(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getPower(),
                car.getYear()
        );
    }

    @Override
    public AssessedValueCarDto toAssessedValueCarDto(AssessedValueCar assessedValueCar) {
        return null;
    }

    @Override
    public Long getId(Car car) {
        return car.getId();
    }

    @Override
    public Long getIdCollateral(AssessedValueCar assessedValueCar) {
        return assessedValueCar.getIdCollateral();
    }

    @Override
    public CarDto toCarDtoOfCollateralWithAssessedValueCar
            (CollateralWithAssessedValueCar collateralWithAssessedValueCar) {
        return new CarDto(
                collateralWithAssessedValueCar.getId(),
                collateralWithAssessedValueCar.getBrand(),
                collateralWithAssessedValueCar.getModel(),
                collateralWithAssessedValueCar.getPower(),
                collateralWithAssessedValueCar.getYear()
        ); }

    @Override
    public AssessedValueCarDto toAssessedValueCarDtoOfCollateralWithAssessedValueCar
            (CollateralWithAssessedValueCar collateralWithAssessedValueCar) {
        return new AssessedValueCarDto(
                collateralWithAssessedValueCar.getValue()
        );
    }

    @Override
    public CollateralWithAssessedValueCar toCollateralAssessedValueCar
            (CarDto carDto, AssessedValueCarDto assessedValueCarDto) {
        return new CollateralWithAssessedValueCar(
                carDto.getId(),
                carDto.getBrand(),
                carDto.getModel(),
                carDto.getPower(),
                carDto.getYear(),
                assessedValueCarDto.getValue()
        );
    }
}
