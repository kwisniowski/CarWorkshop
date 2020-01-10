package com.projects.carworkshop.dto;

import com.projects.carworkshop.domain.InvoiceItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {
    private long id;
    private long customerId;
    private int paymentPeriod;
    private LocalDate paymentLimitDate;
    private boolean paid;
    private Double totalCost;
    private long repairId;
    private List<InvoiceItemDto> items;
}
