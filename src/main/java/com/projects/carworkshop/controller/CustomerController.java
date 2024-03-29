package com.projects.carworkshop.controller;

import com.projects.carworkshop.domain.ApplicationEvent;
import com.projects.carworkshop.dto.CustomerDto;
import com.projects.carworkshop.exception.NotFoundException;
import com.projects.carworkshop.fasade.CustomerFasade;
import com.projects.carworkshop.service.ApplicationEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value="/customers")
    public List<CustomerDto> getUsers() {
        return fasade.fetchCustomers();
    }

    @GetMapping(value="/customers/{customerId}")
    public CustomerDto getCustomer(@PathVariable Long customerId) throws NotFoundException {
        return fasade.fetchCustomer(customerId).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId){
        CustomerDto tempCustomer = fasade.fetchCustomer(customerId).orElse(null);
        if (tempCustomer!=null) {
            applicationEventService.saveEvent(new ApplicationEvent(ApplicationEvent.EventType.DELETED,
                    "Customer ("+tempCustomer.getLastname()+", "+
                    tempCustomer.getFirstname()+") was deleted from database"));
        }
        fasade.deleteCustomer(customerId);
    };

    @PutMapping(value ="/customers")
    public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto tempCustomer = fasade.updateCustomer(customerDto);
        if (tempCustomer!=null) {
            applicationEventService.saveEvent(new ApplicationEvent(ApplicationEvent.EventType.UPDATED,
                    "Customer (" + tempCustomer.getId() + ") was updated"));
        }
        return tempCustomer;
    }

    @PostMapping(value = "/customers", consumes = APPLICATION_JSON_VALUE)
    public void createCustomer(@RequestBody CustomerDto customerDto) {
        fasade.createCustomer(customerDto);
        applicationEventService.saveEvent(new ApplicationEvent(ApplicationEvent.EventType.CREATED,
                "Customer ("+customerDto.getLastname()+", "+
                        customerDto.getFirstname()+") was created"));
    }
}