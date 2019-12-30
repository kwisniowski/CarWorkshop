package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RentRepository extends CrudRepository<Rent,Long> {

    Optional<Rent> findById(long rentId);

    @Override
    List<Rent> findAll();

    Rent save(Rent rent);

    void deleteById(long rentId);

    @Override
    long count();
}
