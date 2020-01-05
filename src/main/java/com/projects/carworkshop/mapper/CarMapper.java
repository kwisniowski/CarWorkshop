package com.projects.carworkshop.mapper;

import com.projects.carworkshop.domain.Car;
import com.projects.carworkshop.dto.CarDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

    public Car mapToCar(final CarDto carDto) {
        return new Car(
                carDto.getId(), carDto.getBrand(),carDto.getModel(),carDto.getManufactureYear(), carDto.getVinNumber(),
                carDto.getEngineSize(),carDto.getPlateNumber(),carDto.getBodyType(),carDto.getOwner(),carDto.getRepairs());
    }

    public CarDto mapToCarDto(final Car car) {
        return new CarDto(
                car.getId(), car.getBrand(),car.getModel(),car.getManufactureYear(), car.getVinNumber(),
                car.getEngineSize(),car.getPlateNumber(),car.getBodyType(),car.getOwner(),car.getRepairs());
    }

    public List<CarDto> mapoCarDtoList(final List<Car> cars) {
        return cars.stream()
                .map(c-> new CarDto(c.getId(), c.getBrand(),c.getModel(),c.getManufactureYear(), c.getVinNumber(),
                        c.getEngineSize(),c.getPlateNumber(),c.getBodyType(),c.getOwner(),c.getRepairs()))
                .collect(Collectors.toList());
    }
}
