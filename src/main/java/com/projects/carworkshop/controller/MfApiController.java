package com.projects.carworkshop.controller;

import com.projects.carworkshop.mfApi.MfApiClient;
import com.projects.carworkshop.mfApi.MfApiResponseDto;
import com.projects.carworkshop.mfApi.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/carworkshop/api/")
@CrossOrigin("*")
public class MfApiController {

    @Autowired
    MfApiClient client;

    @GetMapping(value = "getCustomerInfoByNip/{requestNip}")
    public SubjectDto getCustomerInfoByNip(@PathVariable String requestNip) {
        MfApiResponseDto response = client.getCustomerInfoFromMFByNip(requestNip);
        return response.getResult().getSubject();
    }

    @GetMapping(value = "getCustomerInfoByRegon/{requestRegon}")
    public SubjectDto getCustomerInfoByRegon(@PathVariable String requestRegon) {
        MfApiResponseDto response = client.getCustomerInfoFromMFByRegon(requestRegon);
        return response.getResult().getSubject();
    }

    @GetMapping(value = "checkIsCustomerActive/{requestNip}")
    public boolean checkIfCustomerIsActive(@PathVariable String requestNip) {
        return client.getCustomerActivityStatus(requestNip);
    }
}
