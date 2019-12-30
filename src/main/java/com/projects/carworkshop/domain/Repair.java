package com.projects.carworkshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="REPAIRS")
public class Repair {
    @Column(name="REPAIR_ID")
    @GeneratedValue
    @Id
    @NotNull
    private long id;
    @Column(name="CAR")
    @NotNull
    private Car car;
    @Column(name="START_DATE")
    @NotNull
    private LocalDate startDate;
    @Column(name="END_DATE")
    private LocalDate endDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="JOIN_SPARE_PARTS_REPAIRS",
            joinColumns = {@JoinColumn(name="REPAIR_ID", referencedColumnName = "REPAIR_ID")},
            inverseJoinColumns = {@JoinColumn(name="SPARE_PART_ID", referencedColumnName = "SPARE_PART_ID")}
    )
    private Map<SparePart,Integer> spareParts;

    private double totalCost;

    public Repair(Car car, LocalDate startDate) {
        this.car = car;
        this.startDate = startDate;
        this.endDate = startDate;
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
