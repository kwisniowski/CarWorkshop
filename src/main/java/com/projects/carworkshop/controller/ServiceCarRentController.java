package com.projects.carworkshop.controller;

import com.projects.carworkshop.domain.ApplicationEvent;
import com.projects.carworkshop.domain.ServiceCarRent;
import com.projects.carworkshop.dto.ServiceCarRentDto;
import com.projects.carworkshop.fasade.ServiceCarRentFasade;
import com.projects.carworkshop.service.ApplicationEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/carworkshop/api")
public class ServiceCarRentController {

    @Autowired
    ServiceCarRentFasade fasade;
    @Autowired
    ApplicationEventService applicationEventService;

    @RequestMapping(method = RequestMethod.GET, value = "/rents")
    public List<ServiceCarRentDto> getAllRents() {
        List<ServiceCarRentDto> list = fasade.getAll();
        return list;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rents",consumes = APPLICATION_JSON_VALUE)
    public void createRent (@RequestBody ServiceCarRentDto serviceCarRentDto) {
        fasade.createServiceCarRentDto(serviceCarRentDto);
        applicationEventService.saveEvent(new ApplicationEvent(ApplicationEvent.EventType.CREATED,
                "Service car rent was created from: "+serviceCarRentDto.getStartDate()+" to "+
                serviceCarRentDto.getEndDate()+". Service car is now not avaliable"));
    }

}
