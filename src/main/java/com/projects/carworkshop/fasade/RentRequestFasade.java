package com.projects.carworkshop.fasade;

import com.projects.carworkshop.domain.RentRequest;
import com.projects.carworkshop.dto.RentRequestDto;
import com.projects.carworkshop.mapper.RentRequestMapper;
import com.projects.carworkshop.service.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RentRequestFasade {

    @Autowired
    RentRequestService service;
    @Autowired
    RentRequestMapper mapper;

    public void createRentRequest(RentRequestDto rentRequestDto) {
        service.saveRequest(mapper.mapToRentRequest(rentRequestDto));
    }

    public Optional<RentRequestDto> fetchRequest(Long rentRequestId) {
        return Optional.ofNullable(mapper.mapToRentReqestDto(service.getRequest(rentRequestId).orElse(null)));
    }

    public List<RentRequestDto> fetchAllRentRequests() {
        return mapper.mapToRentRequestDtoList(service.getAllRentRequests());
    };


    public long countRequests() {
        return service.countRequests();
    };

    public void deleteRequest(Long rentRequestId) {
        service.deleteRequest(rentRequestId);
    };

    public RentRequestDto updateRentRequest(RentRequestDto rentRequestDto) {
        return mapper.mapToRentReqestDto(service.saveRequest(mapper.mapToRentRequest(rentRequestDto)));
    }
}
