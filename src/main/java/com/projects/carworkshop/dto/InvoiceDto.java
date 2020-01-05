package com.projects.carworkshop.dto;

import com.projects.carworkshop.domain.Customer;
import com.projects.carworkshop.domain.InvoiceItem;
import com.projects.carworkshop.domain.Repair;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {
    private long id;
    private Customer customer;
    private int paymentPeriod;
    private LocalDate paymentLimitDate;
    private boolean paid;
    private Double totalCost;
    private Repair repair;
    private List<InvoiceItem> items;
}
