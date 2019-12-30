package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.SparePart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface SparePartRepository extends CrudRepository<SparePart, Long> {

    @Override
    Optional<SparePart> findById(Long sparePartId);

    List<SparePart> findAll();

    SparePart save(SparePart sparePart);

    @Override
    void deleteById(Long sparePartId);

    @Override
    long count();
}
