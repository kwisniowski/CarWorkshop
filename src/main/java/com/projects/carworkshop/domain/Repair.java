package com.projects.carworkshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Repair {
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private Map<SparePart,Integer> spareParts;
    private double totalCost;

    public Repair(Car car, LocalDate startDate) {
        this.car = car;
        this.startDate = startDate;
        this.spareParts = new HashMap<>();
    }

    private void calculateTotalCost() {
        if (!spareParts.isEmpty()) {
            this.totalCost = spareParts.entrySet().stream()
                    .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
                    .sum();
        }
        else setTotalCost(0);
    }

    public void endRepair() {
        this.setEndDate(LocalDate.now());
        calculateTotalCost();
    }


}
