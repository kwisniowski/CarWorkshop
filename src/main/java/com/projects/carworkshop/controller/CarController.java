package com.projects.carworkshop.controller;

import com.projects.carworkshop.domain.ApplicationEvent;
import com.projects.carworkshop.domain.Car;
import com.projects.carworkshop.dto.CarDto;
import com.projects.carworkshop.dto.CustomerDto;
import com.projects.carworkshop.exception.NotFoundException;
import com.projects.carworkshop.fasade.CarFasade;
import com.projects.carworkshop.mapper.CarMapper;
import com.projects.carworkshop.mapper.CustomerMapper;
import com.projects.carworkshop.repository.CarRepository;
import com.projects.carworkshop.repository.CustomerRepository;
import com.projects.carworkshop.service.ApplicationEventService;
import com.projects.carworkshop.service.CarService;
import com.projects.carworkshop.service.CustomerService;
import jdk.nashorn.internal.ir.Optimistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/carworkshop/api")
public class CarController {

    @Autowired
    CarFasade fasade;

    @Autowired
    ApplicationEventService applicationEventService;

    @RequestMapping(method = RequestMethod.GET, value="/cars")
    public List<CarDto> getCars() {
        return fasade.fetchAllCars();
    }

    @RequestMapping(method = RequestMethod.GET, value="/cars/{carId}")
    public CarDto getCar(@PathVariable Long carId) throws NotFoundException {
        return fasade.fetchCar(carId).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cars/{carId}")
    public void deleteCar(@PathVariable Long carId){
        CarDto tempCar = fasade.fetchCar(carId).orElse(null);
        if (tempCar!=null) {
            applicationEventService.saveEvent(new ApplicationEvent(ApplicationEvent.EventType.DELETED, LocalDate.now(),
                    LocalTime.now(), "Car ("+tempCar.getPlateNumber()+") was deleted from database"));
        }
        fasade.deleteCar(carId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cars")
    public CarDto updateCar(@RequestBody CarDto carDto) {
        return fasade.updateCar(carDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cars", consumes = APPLICATION_JSON_VALUE)
    public void createCar(@RequestBody CarDto carDto) {
        fasade.createCar(carDto);
    }
}
