package com.projects.carworkshop.service;

import com.projects.carworkshop.domain.ServiceCarRent;
import com.projects.carworkshop.repository.ServiceCarRentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCarRentService {
    @Autowired
    ServiceCarRentRepository serviceCarRentRepository;

    public List<ServiceCarRent> getAllServiceCarRents() {
        return serviceCarRentRepository.findAll();
    }

    public void saveServiceCarRent(ServiceCarRent serviceCarRent) {
        serviceCarRentRepository.save(serviceCarRent);
    }
}
