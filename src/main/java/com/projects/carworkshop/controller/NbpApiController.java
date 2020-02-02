package com.projects.carworkshop.controller;

import com.projects.carworkshop.nbpApi.NbpApiClient;
import com.projects.carworkshop.nbpApi.NbpApiResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/carworkshop/api/")
public class NbpApiController {

    @Autowired
    NbpApiClient client;

    @GetMapping(value = "currency/{code}")
    public double getFactor(@PathVariable String code) {
        NbpApiResponseDto nbpApiResponseDto = client.getCurrentCurrencyFactor(code);
        return Double.valueOf(nbpApiResponseDto.getRates()[0].getMid());
    }
}
