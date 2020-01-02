package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.InvoiceItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface InvoiceItemRepository extends CrudRepository<InvoiceItem, Long> {
}
