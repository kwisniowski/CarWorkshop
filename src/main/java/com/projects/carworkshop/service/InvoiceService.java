package com.projects.carworkshop.service;

import com.projects.carworkshop.domain.Invoice;
import com.projects.carworkshop.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository repository;

    public Optional<Invoice> getInvoice(Long invoiceId) {
        return repository.findById(invoiceId);
    }

    public List<Invoice> getAllInvoices() {
        return repository.findAll();
    }

    public List<Invoice> getAllPaidInvoices() {
        return repository.findAllByPaidIsTrue();
    }

    public List<Invoice> getAllUnPaidInvoices() {
        return repository.findAllByPaidIsFalse();
    }

    public Invoice saveInvoice (Invoice invoice) {
        return repository.save(invoice);
    }

    public void deleteInvoice (Long invoiceId) {
        repository.deleteById(invoiceId);
    }

}
