package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.Repair;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RepairRepository extends CrudRepository<Repair, Long> {
    @Override
    Repair save(Repair repair);

    @Override
    Optional<Repair> findById(Long repairId);

    @Override
    List<Repair> findAll();

    @Override
    long count();

    @Override
    void deleteById(Long repairId);
}
