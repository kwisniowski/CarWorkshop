package com.projects.carworkshop.controller;

import com.projects.carworkshop.config.MailConfig;
import com.projects.carworkshop.domain.Mail;
import com.projects.carworkshop.dto.RentRequestDto;
import com.projects.carworkshop.exception.NotFoundException;
import com.projects.carworkshop.fasade.RentRequestFasade;
import com.projects.carworkshop.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/carworkshop/api")
public class RentRequestController {

    @Autowired
    RentRequestFasade fasade;
    @Autowired
    MailService mailService;
    @Autowired
    MailConfig mailConfig;


    @RequestMapping(method = RequestMethod.GET, value = "/rentRequests")
    public List<RentRequestDto> getRentRequests() {
            return fasade.fetchAllRentRequests();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rentRequests/{rentRequestId}")
    public RentRequestDto getRentRequest(@PathVariable Long rentRequestId) throws NotFoundException {
        return fasade.fetchRequest(rentRequestId).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rentRequests/count")
    public long getRequestsCount() {
        return fasade.countRequests();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/rentRequests/{rentRequestId}")
    public void deleteRentRequest(@PathVariable long rentRequestId) {
        fasade.deleteRequest(rentRequestId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rentRequests")
    public RentRequestDto updateRentRequest(@RequestBody RentRequestDto rentRequest) {
        return fasade.updateRentRequest(rentRequest);
    }

    @RequestMapping(method = RequestMethod.POST,value ="/rentRequests", consumes = APPLICATION_JSON_VALUE)
    public void createRequest(@RequestBody RentRequestDto rentRequestDto) {
        fasade.createRentRequest(rentRequestDto);
        mailService.send(new Mail("wisniowski.kacper@gmail.com","New request","New request has been created"));
    }
}
