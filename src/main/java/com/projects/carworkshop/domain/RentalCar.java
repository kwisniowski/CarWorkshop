package com.projects.carworkshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalCar extends Car {
    private boolean rented;
    ArrayList<Rent> rents;

    public RentalCar(CarBrand brand, String model, String manufactureYear, String vinNumber, double engineSize, String plateNumber, CarBodyType bodyType, Customer owner) {
        super(brand, model, manufactureYear, vinNumber, engineSize, plateNumber, bodyType, owner);
        this.rented = false;
        this.rents = new ArrayList<>();
    }
}
