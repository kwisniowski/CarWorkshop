package com.projects.carworkshop.controller;

import com.projects.carworkshop.dto.RentRequestDto;
import com.projects.carworkshop.exception.NotFoundException;
import com.projects.carworkshop.fasade.RentRequestFasade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/carworkshop/api")
public class RentRequestController {

    @Autowired
    RentRequestFasade fasade;

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

    @RequestMapping(method = RequestMethod.DELETE, value = "/rentRequests")
    public void deleteRentRequest(@PathVariable long rentRequestId) {
        fasade.deleteRequest(rentRequestId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rentRequests")
    public RentRequestDto updateRentRequest(@RequestBody RentRequestDto rentRequest) {
        return fasade.updateRentRequest(rentRequest);
    }

    @RequestMapping(method = RequestMethod.POST,value ="/rentRequests")
    public void createRequest(@RequestBody RentRequestDto rentRequestDto) {
        fasade.createRentRequest(rentRequestDto);
    }
}
