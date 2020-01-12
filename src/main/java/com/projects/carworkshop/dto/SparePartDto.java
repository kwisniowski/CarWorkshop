package com.projects.carworkshop.dto;

import com.projects.carworkshop.domain.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class SparePartDto {
    private long id;
    private Car.CarBrand carBrand;
    private String model;
    private String manufacturer;
    private double price;
}
