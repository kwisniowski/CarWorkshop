package com.projects.carworkshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RepairDto {
        private long id;
        private long carId;
        private LocalDate startDate;
        private LocalDate endDate;
        private double totalCost;
}

