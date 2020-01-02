package com.projects.carworkshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="RENTAL_CARS")
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalCar {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="ID")
    private long id;

    @Column(name="BRAND")
    @Enumerated(EnumType.STRING)
    private Car.CarBrand brand;

    @Column(name="MODEL")
    private String model;

    @Column(name="MANUFACTURE_YEAR")
    private String manufactureYear;

    @Column(name="VIN_NUMBER")
    private String vinNumber;

    @Column(name="ENGINE_SIZE")
    private double engineSize;

    @Column(name="PLATE_NUMBER")
    private String plateNumber;

    @Column(name="BODY_TYPE")
    private Car.CarBodyType bodyType;
    private boolean rented;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "car",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Rent> rents;

    public RentalCar(Car.CarBrand brand, String model, String manufactureYear, String vinNumber, double engineSize, String plateNumber, Car.CarBodyType bodyType) {
        this.brand = brand;
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.vinNumber = vinNumber;
        this.engineSize = engineSize;
        this.plateNumber = plateNumber;
        this.bodyType = bodyType;
        this.rented = false;
        this.rents = new ArrayList<>();
    }
}
