package com.projects.carworkshop.service;

import com.projects.carworkshop.domain.SparePart;
import com.projects.carworkshop.repository.SparePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SparePartService {

    @Autowired
    SparePartRepository repository;

    public List<SparePart> getAll() {
        return repository.findAll();
    }

    public Optional<SparePart> getOne(Long sparePartId) {
        return repository.findById(sparePartId);
    };


    public SparePart save(SparePart sparePart) {
        return repository.save(sparePart);
    };

    public void deleteById(Long sparePartId) {
        repository.deleteById(sparePartId);
    };

    public long count() {
        return repository.count();
    };
}
