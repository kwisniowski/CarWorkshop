package com.projects.carworkshop.mapper;

import com.projects.carworkshop.domain.ServiceCarRent;
import com.projects.carworkshop.dto.ServiceCarRentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceCarRentMapper {

    public ServiceCarRent mapToServiceCarRent (ServiceCarRentDto serviceCarRentDto) {
        return new ServiceCarRent(serviceCarRentDto.getId(), serviceCarRentDto.getStartDate(),serviceCarRentDto.getEndDate());
    }

    public ServiceCarRentDto maptoServiceCarRentDto (ServiceCarRent serviceCarRent) {
        return new ServiceCarRentDto(serviceCarRent.getId(), serviceCarRent.getStartDate(), serviceCarRent.getEndDate());
    }

    public List<ServiceCarRent> mapToServiceCarRentList (List<ServiceCarRentDto> serviceCarRentDtos) {
        return serviceCarRentDtos.stream()
                .map(serviceCarRentDto -> new ServiceCarRent(serviceCarRentDto.getId(), serviceCarRentDto.getStartDate(),serviceCarRentDto.getEndDate()))
                .collect(Collectors.toList());
    }

    public List<ServiceCarRentDto> mapToServiceCarRentDtoList (List<ServiceCarRent> serviceCarRents) {
        return serviceCarRents.stream()
                .map(serviceCarRent -> new ServiceCarRentDto(serviceCarRent.getId(), serviceCarRent.getStartDate(),serviceCarRent.getEndDate()))
                .collect(Collectors.toList());
    }
}
