package com.projects.carworkshop.mapper;

import com.projects.carworkshop.domain.SparePart;
import com.projects.carworkshop.dto.SparePartDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SparePartMapper {

    public SparePart mapToSparePart(final SparePartDto sparePartDto) {
        return new SparePart(sparePartDto.getId(),sparePartDto.getCarBrand(),sparePartDto.getModel(),
                sparePartDto.getManufacturer(),sparePartDto.getPrice());
    }

    public SparePartDto mapToSparePartDto(final SparePart sparePart) {
        return new SparePartDto(sparePart.getId(),sparePart.getCarBrand(),sparePart.getModel(),sparePart.getManufacturer(),
                sparePart.getPrice());
    }

    public List<SparePart> mapToSparePartList(final List<SparePartDto> sparePartDtos) {
        return sparePartDtos.stream()
                .map(sparePartDto -> new SparePart(sparePartDto.getId(),sparePartDto.getCarBrand(),sparePartDto.getModel(),
                        sparePartDto.getManufacturer(),sparePartDto.getPrice()))
                .collect(Collectors.toList());
    }

    public List<SparePartDto> mapToSparePartDtoList(final List<SparePart> spareParts) {
        return spareParts.stream()
                .map(sparePartDto -> new SparePartDto(sparePartDto.getId(),sparePartDto.getCarBrand(),sparePartDto.getModel(),
                        sparePartDto.getManufacturer(),sparePartDto.getPrice()))
                .collect(Collectors.toList());
    }


}
