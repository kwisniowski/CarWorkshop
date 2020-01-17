package com.projects.carworkshop.mapper;

import com.projects.carworkshop.domain.RentRequest;
import com.projects.carworkshop.dto.RentRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentRequestMapper {

    public RentRequest mapToRentRequest(RentRequestDto rentRequestDto) {
        return new RentRequest(rentRequestDto.getId(),rentRequestDto.getCustomerName(),rentRequestDto.getReqRentStartDate(),
                rentRequestDto.getReqRentEndDate());
    }

    public RentRequestDto mapToRentReqestDto(RentRequest rentRequest) {
        return new RentRequestDto(rentRequest.getId(), rentRequest.getCustmerName(), rentRequest.getReqRentStartDate(),
                rentRequest.getReqRentEndDate());
    }

    public List<RentRequest> mapToRentRequestList(List<RentRequestDto> rentRequestDtos) {
        return rentRequestDtos.stream()
                .map(rentRequestDto -> new RentRequest(rentRequestDto.getId(),rentRequestDto.getCustomerName(),rentRequestDto.getReqRentStartDate(),
                        rentRequestDto.getReqRentEndDate()))
                .collect(Collectors.toList());
    }

    public List<RentRequestDto> mapToRentRequestDtoList (List<RentRequest> rentRequests) {
        return rentRequests.stream()
                .map(rentRequest -> new RentRequestDto(rentRequest.getId(), rentRequest.getCustmerName(), rentRequest.getReqRentStartDate(),
                        rentRequest.getReqRentEndDate()))
                .collect(Collectors.toList());
    }
}
