package com.projects.carworkshop.dto;

import com.projects.carworkshop.domain.Car;
import com.projects.carworkshop.domain.Invoice;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RepairDto {
        private long id;
        private long carId;
        private LocalDate startDate;
        private LocalDate endDate;
        private long invoiceId;
        private double totalCost;
}

