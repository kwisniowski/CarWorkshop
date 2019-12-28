package com.projects.carworkshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CARS")
public class Car {
    public enum CarBrand {CITROEN,PEUGEOT,RENAULT};
    public enum CarBodyType {HATCHBACK,STATION_WAGON,CABRIO,SEDAN,VAN};

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="ID")
    private long id;
    @Column(name="BRAND")
    private CarBrand brand;
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
    private CarBodyType bodyType;
    @ManyToOne
    @JoinColumn(name="OWNER_ID")
    private Customer owner;

    public Car(CarBrand brand, String model, String manufactureYear, String vinNumber, double engineSize, String plateNumber, CarBodyType bodyType, Customer owner) {
        this.brand = brand;
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.vinNumber = vinNumber;
        this.engineSize = engineSize;
        this.plateNumber = plateNumber;
        this.bodyType = bodyType;
        this.owner = owner;
    }
}
