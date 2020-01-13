package com.projects.carworkshop.mfApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MfApiClient {

    @Autowired
    RestTemplate restTemplate;

    @Value("${mf.api.endpoint")
    private String mfApiEndpoint;

    public MfApiResponse getCustomerInfoFromMF() {
        MfApiResponse response = restTemplate.getForObject("https://wl-api.mf.gov.pl/api/search/nip/6760115479?date=2019-12-27",MfApiResponse.class);
        if (response!=null) {
            return response;
        }
        else return null;
    }
}

