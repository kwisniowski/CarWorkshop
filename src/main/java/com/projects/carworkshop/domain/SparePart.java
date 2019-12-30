package com.projects.carworkshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="SPARE_PARTS")
public class SparePart {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name="SPARE_PART_ID")
    private long id;
    @Column(name="CAR_BRAND")
    @NotNull
    private Car.CarBrand carBrand;
    @Column(name="MODEL")
    @NotNull
    private String model;
    @Column(name="MANUFACTURER")
    @NotNull
    private String manufacturer;
    @Column(name="PRICE")
    @NotNull
    private double price;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "spareParts")
    private List<Repair> repairs;

    public SparePart(@NotNull Car.CarBrand carBrand, @NotNull String model, @NotNull String manufacturer, @NotNull double price) {
        this.carBrand = carBrand;
        this.model = model;
        this.manufacturer = manufacturer;
        this.price = price;
    }
}
