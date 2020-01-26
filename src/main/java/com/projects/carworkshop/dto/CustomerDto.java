package com.projects.carworkshop.dto;

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
        private String emailAddress;
        private String phoneNumber;
        private boolean vipCustomer;
        private boolean companyCustomer;
        private List<CarDto> carDtos;
        private List<InvoiceDto> invoiceDtos;

}
