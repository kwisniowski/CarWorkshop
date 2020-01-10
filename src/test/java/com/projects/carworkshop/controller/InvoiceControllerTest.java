package com.projects.carworkshop.controller;

import com.projects.carworkshop.domain.*;
import com.projects.carworkshop.dto.InvoiceDto;
import com.projects.carworkshop.fasade.InvoiceFasade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvoiceController.class)
@RunWith(SpringRunner.class)
public class InvoiceControllerTest {

    @MockBean
    InvoiceFasade invoiceFasade;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldFetchEmptyInvoiceList() throws Exception {
        //Given
        when(invoiceFasade.fetchAllInvoices()).thenReturn(new ArrayList<>());

        //When
        //Then
        mockMvc.perform(get("/v1/carworkshop/api/invoices")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));

    }

    @Test
    public void shouldFetchInvoicesList() throws Exception {
        //Given
        Customer testCustomer = new Customer(
                "Jan", "Nowak",
                null, null,
                "122212141212", null,
                "kwisniowski@cxsa.pl", "607241199",
                false, false);

        SparePart testSpare1 = new SparePart(Car.CarBrand.PEUGEOT, "206", "Bosh", 230);
        SparePart testSpare2 = new SparePart(Car.CarBrand.CITROEN, "C3", "Bosh", 199);
        InvoiceItem item1 = new InvoiceItem(testSpare1, 3);
        InvoiceItem item2 = new InvoiceItem(testSpare2, 10);
        InvoiceDto invoice1 = new InvoiceDto(1L,1,24,null,false,null,1,null);

        List<InvoiceDto> invoiceDtoList = new ArrayList<>();
        invoiceDtoList.add(invoice1);

        when(invoiceFasade.fetchAllInvoices()).thenReturn(invoiceDtoList);

        //When
        //Then
        mockMvc.perform(get("/v1/carworkshop/api/invoices")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].paymentPeriod",is(24)));
    }

    @Test
    public void shouldFetchPaidInvoices() throws Exception {
        Customer testCustomer = new Customer(
                "Jan", "Nowak",
                null, null,
                "122212141212", null,
                "kwisniowski@cxsa.pl", "607241199",
                false, false);

        SparePart testSpare1 = new SparePart(Car.CarBrand.PEUGEOT, "206", "Bosh", 230);
        SparePart testSpare2 = new SparePart(Car.CarBrand.CITROEN, "C3", "Bosh", 199);
        InvoiceItem item1 = new InvoiceItem(testSpare1, 3);
        InvoiceItem item2 = new InvoiceItem(testSpare2, 10);
        InvoiceDto invoice1 = new InvoiceDto(1L,1,24,null,false,null,1,new ArrayList<>());
        InvoiceDto invoice2 = new InvoiceDto(1L,1,24,null,true,null,1,new ArrayList<>());
        InvoiceDto invoice3 = new InvoiceDto(1L, 1,24,null,false,null,1,new ArrayList<>());

        List<InvoiceDto> invoiceDtoList = new ArrayList<>();
        invoiceDtoList.add(invoice1);
        invoiceDtoList.add(invoice2);
        invoiceDtoList.add(invoice3);

        when(invoiceFasade.fetchUnpaidInvoices()).thenReturn(invoiceDtoList);

        mockMvc.perform(get("/v1/carworkshop/api/invoices/paid")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect((jsonPath("$[1].paid",is(false))));

    }


}
