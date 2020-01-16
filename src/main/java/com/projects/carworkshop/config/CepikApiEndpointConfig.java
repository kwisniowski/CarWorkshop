package com.projects.carworkshop.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CepikApiEndpointConfig {

        @Value("cepik.api.endpoint")
        private String cepikApiEndpoint;
}

