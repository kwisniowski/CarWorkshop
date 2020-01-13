package com.projects.carworkshop.controller;

import com.projects.carworkshop.mfApi.MfApiClient;
import com.projects.carworkshop.mfApi.MfApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/carworkshop/mfapi")
public class MfApiController {

    @Autowired
    MfApiClient client;

    @RequestMapping(method = RequestMethod.GET, value = "getCustomerInfo")
    public void getCustomerInfo() {
        MfApiResponse response = client.getCustomerInfoFromMF();
        System.out.println(response.getResult().getSubject().getName());
    }
}
