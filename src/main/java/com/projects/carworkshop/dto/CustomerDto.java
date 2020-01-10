package com.projects.carworkshop.dto;

import com.projects.carworkshop.domain.Car;
import com.projects.carworkshop.domain.Invoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
        private long id;
        private String firstname;
        private String lastname;
        private String company;
        private String nipNumber;
        private String accountNumber;
        private String regonNumber;
        private String emailAdress;
        private String phoneNumber;
        private boolean vipCustomer;
        private boolean companyCustomer;
        private List<CarDto> cars;
        private List<InvoiceDto> invoices;

}
