package com.projects.carworkshop.dto;

import com.projects.carworkshop.domain.Invoice;
import com.projects.carworkshop.domain.SparePart;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class InvoiceItemDto {

    private long id;
    private long sparePartId;
    private int quantity;
    private long invoiceId;
}