package com.projects.carworkshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name="RENTS")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rent {
    @Id
    @Column(name="ID")
    @GeneratedValue
    @NotNull
    private long id;
    @Column(name="RENT_DATE")
    private LocalDate rentDate;
    @Column(name="RETURN_DATE")
    private LocalDate returnDate;
    @Column(name="RENT_COUNTER")
    private int distanceCounterAtRent;
    @Column(name="RETURN_COUNTER")
    private int distanceCounterAtReturn;
    @Column(name="KM_PRICE")
    private double kmPrice;
    @Column(name="DAY_PRICE")
    private double dayPrice;
    @Column(name="RENT_COST")
    private double rentCost;
    @ManyToOne
    @JoinColumn(name="CAR_ID")
    private RentalCar car;

    public Rent(RentalCar car, int distanceCounterAtRent, double kmPrice, double dayPrice) {
        this.car = car;
        this.rentDate = LocalDate.now();
        this.distanceCounterAtRent = distanceCounterAtRent;
        this.kmPrice = kmPrice;
        this.dayPrice = dayPrice;
    }

    private void calculateRentCost() {
        if ((distanceCounterAtRent!=0)&&(distanceCounterAtReturn!=0)&&(returnDate!=null)&&(rentDate!=null)) {
            setRentCost(((distanceCounterAtReturn-distanceCounterAtRent)*kmPrice +
                    ((returnDate.compareTo(rentDate)+1)*dayPrice)));
        }
        else setRentCost(0);
    }

    public void endRent(int distanceCounter) {
        setDistanceCounterAtReturn(distanceCounter);
        setReturnDate(LocalDate.now());
        calculateRentCost();
    }
}
