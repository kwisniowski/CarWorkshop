package com.projects.carworkshop.controller;

import com.projects.carworkshop.domain.ApplicationEvent;
import com.projects.carworkshop.dto.SparePartDto;
import com.projects.carworkshop.exception.NotFoundException;
import com.projects.carworkshop.fasade.SparePartFasade;
import com.projects.carworkshop.service.ApplicationEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/carworkshop/api")
public class SparePartController {

    @Autowired
    SparePartFasade fasade;

    @Autowired
    ApplicationEventService applicationEventService;

    @GetMapping(value="/spares")
    public List<SparePartDto> getSpareParts() {
        return fasade.getAllSPareParts();
    }

    @GetMapping(value="/spares/{sparePartId}")
    public SparePartDto getSparePart(@PathVariable Long sparePartId) throws NotFoundException {
        return fasade.getOne(sparePartId).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/spares/{sparePartId}")
    public void deleteSparePart(@PathVariable Long sparePartId){
        SparePartDto sparePartDto = fasade.getOne(sparePartId).orElse(null);
        if (sparePartDto!=null) {
            applicationEventService.saveEvent(new ApplicationEvent(ApplicationEvent.EventType.DELETED,
                    "Spare ("+sparePartDto.getModel()+") was deleted from database"));
        }
        fasade.deleteById(sparePartId);
    }

    @PostMapping(value = "/spares", consumes = APPLICATION_JSON_VALUE)
    public void createSparePart(@RequestBody SparePartDto sparePartDto) {
        fasade.save(sparePartDto);
        applicationEventService.saveEvent(new ApplicationEvent(ApplicationEvent.EventType.CREATED,
                "Spare ("+sparePartDto.getModel()+" "+sparePartDto.getManufacturer()+") was created"));
    }

    @PutMapping(value = "/spares", consumes = APPLICATION_JSON_VALUE)
    public void updateSparePart(@RequestBody SparePartDto sparePartDto) {
        fasade.save(sparePartDto);
        applicationEventService.saveEvent(new ApplicationEvent(ApplicationEvent.EventType.UPDATED,
                "Spare ("+sparePartDto.getModel()+" "+sparePartDto.getManufacturer()+") was updated"));
    }




}
