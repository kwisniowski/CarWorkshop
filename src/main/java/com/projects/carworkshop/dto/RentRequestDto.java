package com.projects.carworkshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentRequestDto {
    private long id;
    private String customerName;
    private LocalDate reqRentStartDate;
    private LocalDate reqRentEndDate;
}

