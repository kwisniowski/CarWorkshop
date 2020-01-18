package com.projects.carworkshop.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class NbpApiConfig {

    @Value("${nbp.api.endpoint}")
    private String nbpApiEndpoint;
}
