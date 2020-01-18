package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.ServiceCarRent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceCarRentRepository extends CrudRepository<ServiceCarRent, Long> {

    @Override
    ServiceCarRent save(ServiceCarRent serviceCarRent);

    @Override
    List<ServiceCarRent> findAll();
}
