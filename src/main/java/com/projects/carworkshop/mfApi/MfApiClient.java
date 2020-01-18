package com.projects.carworkshop.mfApi;

import com.projects.carworkshop.config.MfApiEndpointConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@Controller
public class MfApiClient {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MfApiEndpointConfig endpointConfig;

    public MfApiResponseDto getCustomerInfoFromMFByNip(String requestNip) {
        URI url = UriComponentsBuilder.fromHttpUrl(endpointConfig.getMfApiEndpoint()+endpointConfig.getGetByNipAddress()+requestNip)
                .queryParam("date",LocalDate.now().toString())
                .build().encode().toUri();
        MfApiResponseDto response = restTemplate.getForObject(url, MfApiResponseDto.class);
        if (response!=null) {
            return response;
        }
        else return new MfApiResponseDto();
    }

    public MfApiResponseDto getCustomerInfoFromMFByRegon(String requestRegon) {
        URI url = UriComponentsBuilder.fromHttpUrl(endpointConfig.getMfApiEndpoint()+endpointConfig.getGetByRegonAddress()+requestRegon)
                .queryParam("date",LocalDate.now().toString())
                .build().encode().toUri();
        MfApiResponseDto response = restTemplate.getForObject(url, MfApiResponseDto.class);
        if (response!=null) {
            return response;
        }
        else return new MfApiResponseDto();
    }

    public boolean getCustomerActivityStatus(String requestNip) throws HttpClientErrorException {
        boolean result = false;
        URI url = UriComponentsBuilder.fromHttpUrl(endpointConfig.getMfApiEndpoint()+endpointConfig.getGetByNipAddress()+requestNip)
                .queryParam("date",LocalDate.now().toString())
                .build().encode().toUri();
            MfApiResponseDto response = restTemplate.getForObject(url, MfApiResponseDto.class);
            if (response!=null) {
                if (response.getResult().getSubject().getStatusVat().equalsIgnoreCase("czynny")) {
                    result = true;
                }
                if (response.getResult().getSubject().getStatusVat().equalsIgnoreCase("nieczynny")) {
                    result = false;
                }
            }
            return result;
    }
}

