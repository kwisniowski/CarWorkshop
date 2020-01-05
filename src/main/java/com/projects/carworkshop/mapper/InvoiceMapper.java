package com.projects.carworkshop.mapper;

import com.projects.carworkshop.domain.Invoice;
import com.projects.carworkshop.dto.InvoiceDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceMapper {

    public InvoiceDto mapToInvoiceDto(final Invoice invoice) {
        return new InvoiceDto(
                invoice.getId(),invoice.getCustomer(), invoice.getPaymentPeriod(), invoice.getPaymentLimitDate(),
                invoice.isPaid(),invoice.getTotalCost(),invoice.getRepair(),invoice.getItems());
    }

    public Invoice mapToInvoice(final InvoiceDto invoiceDto) {
        return new Invoice(
                invoiceDto.getId(),invoiceDto.getCustomer(), invoiceDto.getPaymentPeriod(), invoiceDto.getPaymentLimitDate(),
                invoiceDto.isPaid(),invoiceDto.getTotalCost(),invoiceDto.getRepair(),invoiceDto.getItems());
    }

    public List<InvoiceDto> mapToInvoiceDtoList(final List<Invoice> invoices) {
        return invoices.stream()
                .map(i-> new InvoiceDto(i.getId(),i.getCustomer(), i.getPaymentPeriod(), i.getPaymentLimitDate(),
                        i.isPaid(),i.getTotalCost(),i.getRepair(),i.getItems()))
                .collect(Collectors.toList());
    }
}
