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
@Table(name="CAR_RENT_REQUESTS")
public class RentRequest {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    @NotNull
    private long id;

    @Column(name="REQUEST_CUSTOMER")
    @NotNull
    private String custmerName;

    @Column(name = "START_DATE")
    @NotNull
    private LocalDate reqRentStartDate;

    @Column(name = "END_DATE")
    @NotNull
    private LocalDate reqRentEndDate;
}
