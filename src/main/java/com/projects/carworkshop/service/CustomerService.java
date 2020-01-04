package com.projects.carworkshop.service;

import com.projects.carworkshop.domain.Customer;
import com.projects.carworkshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository repository;

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Optional<Customer> getCustomer(Long customerId) {
        return repository.findById(customerId);
    }

    public void deleteCustomer(Long customerId) {
        repository.deleteById(customerId);
    }

    public Customer save(Customer customer) {
        return repository.save(customer);
    }
}
