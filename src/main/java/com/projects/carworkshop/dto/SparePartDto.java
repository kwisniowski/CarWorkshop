package com.projects.carworkshop.dto;

import com.projects.carworkshop.domain.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SparePartDto {
    private long id;
    private Car.CarBrand carBrand;
    private String model;
    private String manufacturer;
    private double price;
}
