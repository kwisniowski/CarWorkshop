package com.projects.carworkshop.fasade;

import com.projects.carworkshop.dto.CustomerDto;
import com.projects.carworkshop.mapper.CustomerMapper;
import com.projects.carworkshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerFasade {
    @Autowired
    CustomerService service;
    @Autowired
    CustomerMapper mapper;

    public List<CustomerDto> fetchCustomers () {
        return mapper.mapToCustomerDtoList(service.getAllCustomers());
    }

    public Optional<CustomerDto> fetchCustomer(Long customerId){
        return Optional.ofNullable(mapper.mapToCustomerDto(service.getCustomer(customerId).orElse(null)));
    }

    public void deleteCustomer(Long customerId){
        service.deleteCustomer(customerId);
    }

    public CustomerDto updateCustomer(CustomerDto customerDto) {
        return mapper.mapToCustomerDto(service.save(mapper.mapToCustomer(customerDto)));
    }

    public void createCustomer(CustomerDto customerDto) {
        service.save(mapper.mapToCustomer(customerDto));
    }



}
