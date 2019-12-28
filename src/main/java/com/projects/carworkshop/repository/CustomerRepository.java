package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    Optional<Customer> findById(long userId);

    @Override
    List<Customer> findAll();

    @Override
    Customer save(Customer customer);

    void deleteById(long customerId);

    long count();
}
