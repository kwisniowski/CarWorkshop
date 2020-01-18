package com.projects.carworkshop.nbpApi;

import com.projects.carworkshop.config.NbpApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
public class NbpApiClient {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    NbpApiConfig nbpApiConfig;

    public NbpApiResponseDto getCurrentCurrencyFactor(String currencyCode) {
        URI url = UriComponentsBuilder.fromHttpUrl(nbpApiConfig.getNbpApiEndpoint()+currencyCode+"/")
                .build().encode().toUri();
        System.out.println(url);
        NbpApiResponseDto response = restTemplate.getForObject(url, NbpApiResponseDto.class);
        if (response!=null) {
            System.out.println(response.getCode()+" + "+response.getRates()[0].getMid());
            return response;
        }
        else return new NbpApiResponseDto();
    }

}
