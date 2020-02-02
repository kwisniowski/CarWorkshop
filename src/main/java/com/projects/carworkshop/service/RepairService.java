package com.projects.carworkshop.service;

import com.projects.carworkshop.domain.Car;
import com.projects.carworkshop.domain.Repair;
import com.projects.carworkshop.repository.CarRepository;
import com.projects.carworkshop.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RepairService {

    @Autowired
    RepairRepository repository;

    public List<Repair> getRepairs() {
        return repository.findAll();
    }

    public Optional<Repair> getRepair(Long repairId) {
        return repository.findById(repairId);
    }

    public void deleteRepair(Long repairId) {
        repository.deleteById(repairId);
    }

    public Repair save(Repair repair) {
        return repository.save(repair);
    }

    public long count() {
        return repository.count();
    };
}