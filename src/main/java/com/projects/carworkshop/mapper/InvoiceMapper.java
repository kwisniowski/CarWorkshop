package com.projects.carworkshop.mapper;

import com.projects.carworkshop.domain.Invoice;
import com.projects.carworkshop.dto.InvoiceDto;
import com.projects.carworkshop.repository.CustomerRepository;
import com.projects.carworkshop.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceMapper {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RepairRepository repairRepository;

    public InvoiceDto mapToInvoiceDto(final Invoice invoice) {
        return new InvoiceDto(
                invoice.getId(),invoice.getCustomer().getId(), invoice.getPaymentPeriod(), invoice.getPaymentLimitDate(),
                invoice.isPaid(),invoice.getTotalCost(),invoice.getRepair().getId(),invoice.getItems());
    }

    public Invoice mapToInvoice(final InvoiceDto invoiceDto) {
        return new Invoice(
                invoiceDto.getId(),customerRepository.findById(invoiceDto.getCustomerId()).orElse(null),
                invoiceDto.getPaymentPeriod(), invoiceDto.getPaymentLimitDate(),
                invoiceDto.isPaid(),invoiceDto.getTotalCost(),repairRepository.findById(invoiceDto.getRepairId()).orElse(null),
                invoiceDto.getItems());
    }

    public List<InvoiceDto> mapToInvoiceDtoList(final List<Invoice> invoices) {
        return invoices.stream()
                .map(i-> new InvoiceDto(i.getId(),i.getCustomer().getId(), i.getPaymentPeriod(), i.getPaymentLimitDate(),
                        i.isPaid(),i.getTotalCost(),i.getRepair().getId(),i.getItems()))
                .collect(Collectors.toList());
    }

    public List<Invoice> mapToInvoiceList(final List<InvoiceDto> invoiceDtoList) {
        return invoiceDtoList.stream()
                .map(i-> new InvoiceDto(i.getId(),customerRepository.findById(i.getCustomerId()).orElse(null), i.getPaymentPeriod(), i.getPaymentLimitDate(),
                        i.isPaid(),i.getTotalCost(),repairRepository.findById(i.getRepairId()).orElse(null),i.getItems()))
                .collect(Collectors.toList());
    }
}
