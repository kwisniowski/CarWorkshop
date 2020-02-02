package com.projects.carworkshop.controller;

import com.projects.carworkshop.mfApi.MfApiResponseDto;
import com.projects.carworkshop.nbpApi.NbpApiClient;
import com.projects.carworkshop.nbpApi.NbpApiResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/carworkshop/api/")
public class NbpApiController {

    @Autowired
    NbpApiClient client;

    @RequestMapping(method = RequestMethod.GET, value = "currency/{code}")
    public double getFactor(@PathVariable String code) {
        NbpApiResponseDto nbpApiResponseDto = client.getCurrentCurrencyFactor(code);
        return Double.valueOf(nbpApiResponseDto.getRates()[0].getMid());
    }
}
