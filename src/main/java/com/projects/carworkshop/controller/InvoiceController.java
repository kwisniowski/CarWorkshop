package com.projects.carworkshop.controller;

import com.projects.carworkshop.dto.InvoiceDto;
import com.projects.carworkshop.exception.NotFoundException;
import com.projects.carworkshop.fasade.InvoiceFasade;
import com.projects.carworkshop.mapper.InvoiceMapper;
import com.projects.carworkshop.repository.InvoiceRepository;
import com.projects.carworkshop.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/carworkshop/api")
@CrossOrigin("*")
public class InvoiceController {
    @Autowired
    InvoiceFasade fasade;

    @RequestMapping(method = RequestMethod.GET, value="/invoices")
    public List<InvoiceDto> getInvoices() {
        return fasade.fetchAllInvoices();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/invoices/paid")
    public List<InvoiceDto> getPaidInvoices() {
        return fasade.fetchPaidInvoices();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/invoices/unpaid")
    public List<InvoiceDto> getUnpaidInvoices() {
        return fasade.fetchUnpaidInvoices();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/invoices/{invoiceId}")
    public InvoiceDto getInvoice(@PathVariable Long invoiceId) throws NotFoundException {
        return fasade.fetchInvoice(invoiceId).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/invoices")
    public void createInvoice(@RequestBody InvoiceDto invoiceDto) {
        fasade.createInvoice(invoiceDto);
    }


}

