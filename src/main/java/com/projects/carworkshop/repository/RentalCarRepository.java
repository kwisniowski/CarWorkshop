package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.RentalCar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RentalCarRepository extends CrudRepository<RentalCar,Long> {

    Optional<RentalCar> findById(long rentalCarId);

    List<RentalCar> findAll();

    RentalCar save(RentalCar rentalCar);

    void deleteById(long rentalCarId);

    @Override
    long count();
}
