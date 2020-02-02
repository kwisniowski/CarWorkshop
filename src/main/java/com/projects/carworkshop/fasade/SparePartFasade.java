package com.projects.carworkshop.fasade;

import com.projects.carworkshop.dto.SparePartDto;
import com.projects.carworkshop.mapper.SparePartMapper;
import com.projects.carworkshop.service.SparePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SparePartFasade {

    @Autowired
    SparePartService service;
    @Autowired
    SparePartMapper mapper;

    public List<SparePartDto> getAllSPareParts() {
        return mapper.mapToSparePartDtoList(service.getAll());
    }

    public Optional<SparePartDto> getOne(Long sparePartId) {
        return Optional.ofNullable(mapper.mapToSparePartDto(service.getOne(sparePartId).orElseThrow(null)));
    };


    public SparePartDto save(SparePartDto sparePartDto) {
        return mapper.mapToSparePartDto(service.save(mapper.mapToSparePart(sparePartDto)));
    };

    public void deleteById(Long sparePartId) {
        service.deleteById(sparePartId);
    };

    public long count() {
        return service.count();
    };

}
