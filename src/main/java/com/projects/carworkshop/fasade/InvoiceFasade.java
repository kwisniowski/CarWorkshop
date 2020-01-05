package com.projects.carworkshop.fasade;

import com.projects.carworkshop.dto.InvoiceDto;
import com.projects.carworkshop.exception.NotFoundException;
import com.projects.carworkshop.mapper.InvoiceMapper;
import com.projects.carworkshop.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Component
public class InvoiceFasade {

    @Autowired
    InvoiceMapper mapper;

    @Autowired
    InvoiceService service;

    public List<InvoiceDto> fetchAllInvoices() {
        return mapper.mapToInvoiceDtoList(service.getAllInvoices());
    }

    public List<InvoiceDto> fetchPaidInvoices() {
        return mapper.mapToInvoiceDtoList(service.getAllPaidInvoices());
    }

    public List<InvoiceDto> fetchUnpaidInvoices() {
        return mapper.mapToInvoiceDtoList(service.getAllUnPaidInvoices());
    }

    public Optional<InvoiceDto> fetchInvoice(Long invoiceId) {
        return Optional.ofNullable(mapper.mapToInvoiceDto(service.getInvoice(invoiceId).orElse(null)));
    }

    public void createInvoice(InvoiceDto invoiceDto) {
        service.saveInvoice(mapper.mapToInvoice(invoiceDto));
    }
}
