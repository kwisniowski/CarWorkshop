package com.projects.carworkshop.dto;

import com.projects.carworkshop.domain.Car;
import com.projects.carworkshop.domain.Customer;
import com.projects.carworkshop.domain.Repair;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private long id;
    private Car.CarBrand brand;
    private String model;
    private String manufactureYear;
    private String vinNumber;
    private double engineSize;
    private String plateNumber;
    private Car.CarBodyType bodyType;
    private Customer owner;
    private List<Repair> repairs;
}
