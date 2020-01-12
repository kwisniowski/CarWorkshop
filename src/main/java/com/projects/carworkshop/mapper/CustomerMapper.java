package com.projects.carworkshop.mapper;

import com.projects.carworkshop.domain.Customer;
import com.projects.carworkshop.dto.CustomerDto;
import com.projects.carworkshop.repository.CarRepository;
import com.projects.carworkshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    @Autowired
    CarRepository carRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CarMapper carMapper;
    @Autowired
    InvoiceMapper invoiceMapper;

    public Customer mapToCustomer (final CustomerDto customerDto) {
        return new Customer(customerDto.getId(),customerDto.getFirstname(),customerDto.getLastname(), customerDto.getCompany(),
                customerDto.getNipNumber(),customerDto.getAccountNumber(),customerDto.getRegonNumber(), customerDto.getEmailAdress(),
                customerDto.getPhoneNumber(),customerDto.isVipCustomer(),customerDto.isCompanyCustomer(),
                carMapper.mapoCarList(customerDto.getCarDtos()), invoiceMapper.mapToInvoiceList(customerDto.getInvoiceDtos()));
    }

    public CustomerDto mapToCustomerDto(final Customer customer) {
        return new CustomerDto(
                customer.getId(),customer.getFirstname(), customer.getLastname(), customer.getCompany(),
                customer.getNipNumber(), customer.getAccountNumber(), customer.getRegonNumber(), customer.getEmailAdress(),
                customer.getPhoneNumber(), customer.isVipCustomer(), customer.isCompanyCustomer(),carMapper.mapoCarDtoList(customer.getCars()),
                invoiceMapper.mapToInvoiceDtoList(customer.getInvoices()));
    }

    public List<CustomerDto> mapToCustomerDtoList (final List<Customer> customerList) {
        return customerList.stream()
                .map(c -> new CustomerDto(c.getId(),c.getFirstname(), c.getLastname(), c.getCompany(),
                        c.getNipNumber(), c.getAccountNumber(), c.getRegonNumber(), c.getEmailAdress(),
                        c.getPhoneNumber(), c.isVipCustomer(), c.isCompanyCustomer(),carMapper.mapoCarDtoList(c.getCars()),
                        invoiceMapper.mapToInvoiceDtoList(c.getInvoices())))
                .collect(Collectors.toList());
    }

    public List<Customer> mapToCustomerList (final List<CustomerDto> customerDtoList) {
        return customerDtoList.stream()
                .map(c-> new Customer(c.getId(),c.getFirstname(), c.getLastname(), c.getCompany(),
                        c.getNipNumber(), c.getAccountNumber(), c.getRegonNumber(), c.getEmailAdress(),
                        c.getPhoneNumber(), c.isVipCustomer(), c.isCompanyCustomer(),carMapper.mapoCarList(c.getCarDtos()),
                        invoiceMapper.mapToInvoiceList(c.getInvoiceDtos())))
                .collect(Collectors.toList());
    }
}
