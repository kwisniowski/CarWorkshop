package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface InvoiceRepository extends CrudRepository<Invoice,Long> {

    @Override
    Invoice save(Invoice invoice);

    @Override
    Optional<Invoice> findById(Long invoiceId);

    @Override
    long count();

    @Override
    void deleteById(Long invoiceId);

    @Override
    List<Invoice> findAll();

    List<Invoice> findAllByPaidIsTrue();

    List<Invoice> findAllByPaidIsFalse();
}
