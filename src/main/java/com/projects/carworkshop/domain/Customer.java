package com.projects.carworkshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CUSTOMERS")
public class Customer {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name="ID")
    private long id;

    @Column(name="FIRSTNAME")
    private String firstname;

    @Column(name="LASTNAME")
    private String lastname;

    @Column(name="COMPANY")
    private String company;

    @Column(name="NIP_NUMBER")
    private String nipNumber;

    @Column(name="ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name="REGON_NUMBER")
    private String regonNumber;

    @Column(name="EMAIL")
    @NotNull
    private String emailAdress;

    @Column(name="PHONE")
    @NotNull
    private String phoneNumber;

    @Column(name="VIP")
    private boolean vipCustomer;

    @Column(name="COMPANY_CUSTOMER")
    private boolean companyCustomer;

    @OneToMany(
            targetEntity = Car.class,
            mappedBy = "owner",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Car> cars;

    @OneToMany(
            targetEntity = Invoice.class,
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Invoice> invoices;

    public Customer(
            String firstname, String lastname, String company, String nipNumber, String accountNumber,
            String regonNumber, @NotNull String emailAdress, @NotNull String phoneNumber, boolean vipCustomer,
            boolean companyCustomer) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.nipNumber = nipNumber;
        this.accountNumber = accountNumber;
        this.regonNumber = regonNumber;
        this.emailAdress = emailAdress;
        this.phoneNumber = phoneNumber;
        this.vipCustomer = vipCustomer;
        this.companyCustomer = companyCustomer;
        this.cars = new ArrayList<>();
        this.invoices = new ArrayList<>();
    }
}
