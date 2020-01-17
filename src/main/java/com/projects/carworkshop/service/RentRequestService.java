package com.projects.carworkshop.service;

import com.projects.carworkshop.domain.RentRequest;
import com.projects.carworkshop.repository.RentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentRequestService {

    @Autowired
    RentRequestRepository repository;
    public RentRequest saveRequest(RentRequest rentRequest) {
        return repository.save(rentRequest);
    }


    public Optional<RentRequest> getRequest(Long rentRequestId){
        return repository.findById(rentRequestId);
    };

    public List<RentRequest> getAllRentRequests() {
        return repository.findAll();
    };


    public long countRequests() {
        return repository.count();
    };

    public void deleteRequest(Long rentRequestId) {
        repository.deleteById(rentRequestId);
    };
}
