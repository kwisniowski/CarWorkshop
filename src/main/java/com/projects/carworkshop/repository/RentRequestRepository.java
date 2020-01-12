package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.RentRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RentRequestRepository extends CrudRepository<RentRequest, Long> {

    @Override
    RentRequest save(RentRequest rentRequest);

    @Override
    Optional<RentRequest> findById(Long rentRequestId);

    @Override
    List<RentRequest> findAll();

    @Override
    long count();

    void deleteById(Long rentRequestId);

}
