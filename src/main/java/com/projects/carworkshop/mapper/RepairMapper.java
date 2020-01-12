package com.projects.carworkshop.mapper;

import com.projects.carworkshop.domain.Repair;
import com.projects.carworkshop.dto.RepairDto;
import com.projects.carworkshop.repository.CarRepository;
import com.projects.carworkshop.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RepairMapper {

    @Autowired
    CarRepository carRepository;
    @Autowired
    InvoiceRepository invoiceRepository;

    public RepairDto mapToRepairDto(final Repair repair) {
        return new RepairDto(repair.getId(), repair.getCar().getId(), repair.getStartDate(), repair.getEndDate(),
                repair.getInvoice().getId(),repair.getTotalCost());
    }

    public Repair mapToRepair(final RepairDto repairDto) {
        return new Repair(repairDto.getId(), carRepository.findById(repairDto.getCarId()).orElse(null),
                repairDto.getStartDate(), repairDto.getEndDate(), invoiceRepository.findById(repairDto.getInvoiceId()).orElse(null),
                repairDto.getTotalCost());
    }

    public List<RepairDto> mapToRepairDtoList(final List<Repair> repairList) {
        return repairList.stream()
                .map(r-> new RepairDto(r.getId(),r.getCar().getId(),r.getStartDate(),r.getEndDate(),r.getInvoice().getId(),
                        r.getTotalCost()))
                .collect(Collectors.toList());
    }

    public List<Repair> mapToRepairList(final List<RepairDto> repairDtoList) {
            return repairDtoList.stream()
                    .map(r -> new Repair(r.getId(), carRepository.findById(r.getCarId()).orElse(null), r.getStartDate(),
                            r.getEndDate(), invoiceRepository.findById(r.getInvoiceId()).orElse(null), r.getTotalCost()))
                    .collect(Collectors.toList());

    }
}
