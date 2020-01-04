package com.projects.carworkshop.controller;

import com.projects.carworkshop.dto.CustomerDto;
import com.projects.carworkshop.exception.NotFoundException;
import com.projects.carworkshop.mapper.CustomerMapper;
import com.projects.carworkshop.repository.CustomerRepository;
import com.projects.carworkshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/carworkshop/api/")
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @Autowired
    CustomerMapper mapper;

    @Autowired
    CustomerService service;

    @RequestMapping(method = RequestMethod.GET, value="getCustomers")
    public List<CustomerDto> getUsers() {
        return mapper.mapToCustomerDtoList(service.getAllCustomers());
    }
    @RequestMapping(method = RequestMethod.GET, value="getCustomer")
    public CustomerDto getCustomer(@RequestParam Long customerId) throws NotFoundException {
        return mapper.mapToCustomerDto(service.getCustomer(customerId).orElseThrow(NotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCustomer")
    public void deleteCustomer(@RequestParam Long customerId){
        service.deleteCustomer(customerId);
    };

    @RequestMapping(method = RequestMethod.PUT, value ="updateCustomer")
    public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto) {
        return mapper.mapToCustomerDto(service.save(mapper.mapToCustomer(customerDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createCustomer", consumes = APPLICATION_JSON_VALUE)
    public void createCustomer(@RequestBody CustomerDto customerDto) {
        service.save(mapper.mapToCustomer(customerDto));
    }
}

