package com.projects.carworkshop.service;

import com.projects.carworkshop.domain.Car;
import com.projects.carworkshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepository repository;

    public List<Car> getCars() {
        return repository.findAll();
    }

    public Optional<Car> getCar(Long carId) {
        return repository.findById(carId);
    }

    public void deleteCar(Long carId) {
        repository.deleteById(carId);
    }

    public Car save(Car car) {
        return repository.save(car);
    }

    public long count() {
        return repository.count();
    }

}
