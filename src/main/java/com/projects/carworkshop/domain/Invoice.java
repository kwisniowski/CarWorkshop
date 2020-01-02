package com.projects.carworkshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="INVOICES")
public class Invoice {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name="ID")
    private long id;

    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;

    @Column(name="PAYMENT_PERIOD")
    private int paymentPeriod;

    @Column(name="PAYMENT_LIMIT_DATE")
    @NotNull
    private LocalDate paymentLimitDate;

    @Column(name="IS_PAID")
    @NotNull
    private boolean paid;

    @Column(name="COST")
    private long totalCost;

    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Repair repair;

    @OneToMany(
            targetEntity = InvoiceItem.class,
            mappedBy = "invoice",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<InvoiceItem> items;

    public Invoice(@NotNull Customer customer, int paymentPeriod) {
        this.customer = customer;
        this.paymentPeriod = paymentPeriod;
        this.paymentLimitDate = LocalDate.now().plus(paymentPeriod, ChronoUnit.DAYS);
        this.paid = false;
        this.totalCost = 0;
        this.items = new ArrayList<InvoiceItem>();
    }
}
