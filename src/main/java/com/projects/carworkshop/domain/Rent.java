package com.projects.carworkshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rent {
    private RentalCar car;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private int distanceCounterAtRent;
    private int distanceCounterAtReturn;
    private double kmPrice;
    private double dayPrice;
    private double rentCost;

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
