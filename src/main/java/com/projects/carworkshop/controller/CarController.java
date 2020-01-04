package com.projects.carworkshop.controller;

import com.projects.carworkshop.domain.Car;
import com.projects.carworkshop.dto.CarDto;
import com.projects.carworkshop.dto.CustomerDto;
import com.projects.carworkshop.exception.NotFoundException;
import com.projects.carworkshop.mapper.CarMapper;
import com.projects.carworkshop.mapper.CustomerMapper;
import com.projects.carworkshop.repository.CarRepository;
import com.projects.carworkshop.repository.CustomerRepository;
import com.projects.carworkshop.service.CarService;
import com.projects.carworkshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/carworkshop/api")
public class CarController {

    @Autowired
    CarRepository repository;

    @Autowired
    CarMapper mapper;

    @Autowired
    CarService service;

    @RequestMapping(method = RequestMethod.GET, value="/cars")
    public List<CarDto> getCars() {
        return mapper.mapoCarDtoList(service.getCars());
    }

    @RequestMapping(method = RequestMethod.GET, value="/cars/{carId}")
    public CarDto getCar(@PathVariable Long carId) throws NotFoundException {
        return mapper.mapToCarDto(service.getCar(carId).orElseThrow(NotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cars/{carId}")
    public void deleteCuar(@PathVariable Long carId){
        service.deleteCar(carId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cars")
    public CarDto updateCar(@RequestBody CarDto carDto) {
        return mapper.mapToCarDto(service.save(mapper.mapToCar(carDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cars", consumes = APPLICATION_JSON_VALUE)
    public void createCar(@RequestBody CarDto carDto) {
        service.save(mapper.mapToCar(carDto));
    }
}
