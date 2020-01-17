package com.projects.carworkshop.controller;

import com.projects.carworkshop.domain.ApplicationEvent;
import com.projects.carworkshop.dto.CarDto;
import com.projects.carworkshop.dto.CustomerDto;
import com.projects.carworkshop.exception.NotFoundException;
import com.projects.carworkshop.fasade.CustomerFasade;
import com.projects.carworkshop.mapper.CustomerMapper;
import com.projects.carworkshop.repository.CustomerRepository;
import com.projects.carworkshop.service.ApplicationEventService;
import com.projects.carworkshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/carworkshop/api")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    CustomerFasade fasade;
    @Autowired
    ApplicationEventService applicationEventService;

    @RequestMapping(method = RequestMethod.GET, value="/customers")
    public List<CustomerDto> getUsers() {
        return fasade.fetchCustomers();
    }

    @RequestMapping(method = RequestMethod.GET, value="/customers/{customerId}")
    public CustomerDto getCustomer(@PathVariable Long customerId) throws NotFoundException {
        return fasade.fetchCustomer(customerId).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId){
        CustomerDto tempCustomer = fasade.fetchCustomer(customerId).orElse(null);
        if (tempCustomer!=null) {
            applicationEventService.saveEvent(new ApplicationEvent(ApplicationEvent.EventType.DELETED, LocalDate.now(),
                    LocalTime.now(), "Customer ("+tempCustomer.getLastname()+", "+
                    tempCustomer.getFirstname()+") was deleted from database"));
        }
        fasade.deleteCustomer(customerId);
    };

    @RequestMapping(method = RequestMethod.PUT, value ="/customers")
    public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto) {
        return fasade.updateCustomer(customerDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customers", consumes = APPLICATION_JSON_VALUE)
    public void createCustomer(@RequestBody CustomerDto customerDto) {
        fasade.createCustomer(customerDto);
    }
}