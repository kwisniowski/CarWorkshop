package com.projects.carworkshop.fasade;

import com.projects.carworkshop.domain.ServiceCarRent;
import com.projects.carworkshop.dto.ServiceCarRentDto;
import com.projects.carworkshop.mapper.ServiceCarRentMapper;
import com.projects.carworkshop.repository.ServiceCarRentRepository;
import com.projects.carworkshop.service.ServiceCarRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class ServiceCarRentFasade {

        @Autowired
        ServiceCarRentService service;
        @Autowired
        ServiceCarRentMapper mapper;

        public List<ServiceCarRentDto> getAll() {
            return mapper.mapToServiceCarRentDtoList(service.getAllServiceCarRents());
        }

        public void createServiceCarRentDto(ServiceCarRentDto serviceCarRentDto) {
            service.saveServiceCarRent(mapper.mapToServiceCarRent(serviceCarRentDto));
        }
}

