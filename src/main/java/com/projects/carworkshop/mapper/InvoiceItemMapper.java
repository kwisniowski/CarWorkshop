package com.projects.carworkshop.mapper;

import com.projects.carworkshop.domain.Invoice;
import com.projects.carworkshop.domain.InvoiceItem;
import com.projects.carworkshop.dto.InvoiceDto;
import com.projects.carworkshop.dto.InvoiceItemDto;
import com.projects.carworkshop.repository.InvoiceRepository;
import com.projects.carworkshop.repository.SparePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceItemMapper {
    @Autowired
    SparePartRepository sparePartRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    public InvoiceItem mapToInvoiceItem(final InvoiceItemDto invoiceItemDto) {
        return new InvoiceItem(invoiceItemDto.getId(),sparePartRepository.findById(invoiceItemDto.getSparePartId()).orElse(null),
                invoiceItemDto.getQuantity(),invoiceRepository.findById(invoiceItemDto.getInvoiceId()).orElse(null));
    }

    public InvoiceItemDto mapToInvoiceItemDto(final InvoiceItem invoiceItem) {
        return new InvoiceItemDto(invoiceItem.getId(),invoiceItem.getSparePart().getId(),
                invoiceItem.getQuantity(),invoiceItem.getInvoice().getId());
    }

    public List<InvoiceItem> mapToInvoiceItemList(final List<InvoiceItemDto> invoiceItemDtos) {
        return invoiceItemDtos.stream()
                .map(i-> new InvoiceItem(i.getId(),sparePartRepository.findById(i.getSparePartId()).orElse(null),
                        i.getQuantity(),invoiceRepository.findById(i.getInvoiceId()).orElse(null)))
                .collect(Collectors.toList());
    }

    public List<InvoiceItemDto> mapToInvoiceItemDtoList(final List<InvoiceItem> invoiceItems) {
        return invoiceItems.stream()
                .map(i-> new InvoiceItemDto(i.getId(), i.getSparePart().getId(),
                        i.getQuantity(),i.getInvoice().getId()))
                .collect(Collectors.toList());
    }


}
