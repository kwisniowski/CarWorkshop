package com.projects.carworkshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name="CAR_ID")
    private Car car;

    @Column(name="START_DATE")
    @NotNull
    private LocalDate startDate;

    @Column(name="END_DATE")
    private LocalDate endDate;

    @Column(name="REPAIR_COST")
    private double totalCost;

    public Repair(Car car, LocalDate startDate) {
        this.car = car;
        this.startDate = startDate;
        this.endDate = startDate;
    }

}
