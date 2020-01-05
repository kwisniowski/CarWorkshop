package com.projects.carworkshop.mapper;

import com.projects.carworkshop.domain.Customer;
import com.projects.carworkshop.dto.CustomerDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {
    public Customer mapToCustomer (final CustomerDto customerDto) {
        return new Customer(
                customerDto.getId(),customerDto.getFirstname(), customerDto.getLastname(), customerDto.getCompany(),
                customerDto.getNipNumber(), customerDto.getAccountNumber(), customerDto.getRegonNumber(), customerDto.getEmailAdress(),
                customerDto.getPhoneNumber(), customerDto.isVipCustomer(), customerDto.isCompanyCustomer(),customerDto.getCars(),
                customerDto.getInvoices());
    }

    public CustomerDto mapToCustomerDto(final Customer customer) {
        return new CustomerDto(
                customer.getId(),customer.getFirstname(), customer.getLastname(), customer.getCompany(),
                customer.getNipNumber(), customer.getAccountNumber(), customer.getRegonNumber(), customer.getEmailAdress(),
                customer.getPhoneNumber(), customer.isVipCustomer(), customer.isCompanyCustomer(),customer.getCars(),
                customer.getInvoices());
    }

    public List<CustomerDto> mapToCustomerDtoList (final List<Customer> customerList) {
        return customerList.stream()
                .map(c -> new CustomerDto(c.getId(),c.getFirstname(), c.getLastname(), c.getCompany(),
                        c.getNipNumber(), c.getAccountNumber(), c.getRegonNumber(), c.getEmailAdress(),
                        c.getPhoneNumber(), c.isVipCustomer(), c.isCompanyCustomer(),c.getCars(),
                        c.getInvoices()))
                .collect(Collectors.toList());
    }
}
