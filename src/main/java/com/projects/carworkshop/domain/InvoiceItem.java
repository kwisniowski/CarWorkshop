package com.projects.carworkshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="INVOICE_ITEMS")
public class InvoiceItem {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "SPARE_PART_ID")
    private SparePart sparePart;

    @Column(name = "QUANTITY")
    @NotNull
    private int quantity;

    @ManyToOne
    @JoinColumn(name="INVOICE_ID")
    private Invoice invoice;

    public InvoiceItem(SparePart sparePart, @NotNull int quantity) {
        this.sparePart = sparePart;
        this.quantity = quantity;
    }

    public double calculateItemCost() {
        return sparePart.getPrice()*quantity;
    }
}
