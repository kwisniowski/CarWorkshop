package com.projects.carworkshop.mapper;

import com.projects.carworkshop.domain.Car;
import com.projects.carworkshop.dto.CarDto;
import com.projects.carworkshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

    @Autowired
    CustomerService customerService;
    @Autowired
    RepairMapper repairMapper;

    public Car mapToCar(final CarDto carDto) {
        return new Car(carDto.getId(),carDto.getBrand(),carDto.getModel(),carDto.getManufactureYear(),carDto.getVinNumber(),
                carDto.getEngineSize(), carDto.getPlateNumber(),carDto.getBodyType(),
                customerService.getCustomer(carDto.getCustomerId()).orElse(null),repairMapper.mapToRepairList(carDto.getRepairDtos()));
    }

    public CarDto mapToCarDto(final Car car) {
        return new CarDto(
                car.getId(), car.getBrand(),car.getModel(),car.getManufactureYear(), car.getVinNumber(),
                car.getEngineSize(),car.getPlateNumber(),car.getBodyType(),car.getOwner().getId(),repairMapper.mapToRepairDtoList(car.getRepairs()));
    }

    public List<CarDto> mapoCarDtoList(final List<Car> cars) {
        return cars.stream()
                .map(c-> new CarDto(c.getId(), c.getBrand(),c.getModel(),c.getManufactureYear(), c.getVinNumber(),
                        c.getEngineSize(),c.getPlateNumber(),c.getBodyType(),c.getOwner().getId(),repairMapper.mapToRepairDtoList(c.getRepairs())))
                .collect(Collectors.toList());
    }

    public List<Car> mapoCarList(final List<CarDto> carsDtoList) {
        return carsDtoList.stream()
                .map(c-> new Car(c.getId(), c.getBrand(),c.getModel(),c.getManufactureYear(), c.getVinNumber(),
                        c.getEngineSize(),c.getPlateNumber(),c.getBodyType(),
                        customerService.getCustomer(c.getCustomerId()).orElse(null),repairMapper.mapToRepairList(c.getRepairDtos())))
                .collect(Collectors.toList());
    }
}
