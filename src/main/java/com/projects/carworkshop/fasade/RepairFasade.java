package com.projects.carworkshop.fasade;

import com.projects.carworkshop.dto.RepairDto;
import com.projects.carworkshop.mapper.RepairMapper;
import com.projects.carworkshop.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RepairFasade {

    @Autowired
    RepairService service;
    @Autowired
    RepairMapper mapper;

    public List<RepairDto> fetchAllRepairs() {
        return mapper.mapToRepairDtoList(service.getRepairs());
    }

    public Optional<RepairDto> fetchRepair(Long repairId) {
        return Optional.ofNullable(mapper.mapToRepairDto(service.getRepair(repairId).orElse(null)));
    }

    public void deleteRepair(Long repairId){
        service.deleteRepair(repairId);
    }

    public RepairDto updateRepair(RepairDto repairDto) {
        return mapper.mapToRepairDto(service.save(mapper.mapToRepair(repairDto)));
    }

    public void createRepair(RepairDto repairDto) {
        service.save(mapper.mapToRepair(repairDto));
    }
}

