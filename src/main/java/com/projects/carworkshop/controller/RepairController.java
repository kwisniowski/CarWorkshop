package com.projects.carworkshop.controller;

import com.projects.carworkshop.domain.ApplicationEvent;

import com.projects.carworkshop.dto.RepairDto;
import com.projects.carworkshop.exception.NotFoundException;
import com.projects.carworkshop.fasade.RepairFasade;
import com.projects.carworkshop.service.ApplicationEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/carworkshop/api")
public class RepairController {

    @Autowired
    RepairFasade fasade;

    @Autowired
    ApplicationEventService applicationEventService;

    @GetMapping(value="/repairs")
    public List<RepairDto> getRepairs() {
        return fasade.fetchAllRepairs();
    }

    @GetMapping(value="/repairs/{repairId}")
    public RepairDto getRepair(@PathVariable Long repairId) throws NotFoundException {
        return fasade.fetchRepair(repairId).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/repairs/{repairId}")
    public void deleteRepair(@PathVariable Long repairId){
        RepairDto tempRepair = fasade.fetchRepair(repairId).orElse(null);
        if (tempRepair!=null) {
            applicationEventService.saveEvent(new ApplicationEvent(ApplicationEvent.EventType.DELETED,
                    "Repair ("+tempRepair.getId()+") was deleted from database"));
        }
        fasade.deleteRepair(repairId);
    }

    @PutMapping(value = "/repairs")
    public RepairDto updateRepair(@RequestBody RepairDto repairDto) {
        RepairDto tempRepairDto = fasade.updateRepair(repairDto);
        if (tempRepairDto!=null) {
            applicationEventService.saveEvent(new ApplicationEvent(ApplicationEvent.EventType.UPDATED,
                    "Repair (" + tempRepairDto.getId() + ") was updated"));
        }
        return tempRepairDto;
    }

    @PostMapping(value = "/repairs", consumes = APPLICATION_JSON_VALUE)
    public void createRepair(@RequestBody RepairDto repairDto) {
        fasade.createRepair(repairDto);
        applicationEventService.saveEvent(new ApplicationEvent(ApplicationEvent.EventType.CREATED,
                "Repair ("+repairDto.getId()+") was created"));
    }
}
