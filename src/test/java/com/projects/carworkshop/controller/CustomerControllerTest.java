package com.projects.carworkshop.controller;

import com.google.gson.Gson;
import com.projects.carworkshop.domain.Customer;
import com.projects.carworkshop.dto.CustomerDto;
import com.projects.carworkshop.fasade.CustomerFasade;
import com.projects.carworkshop.service.ApplicationEventService;
import com.projects.carworkshop.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerFasade customerFasade;

    @MockBean
    ApplicationEventService applicationEventService;

    @Mock
    MailService mailService;

    @Test
    public void shouldFetchEmptyCustomersList() throws Exception {
        //Given
        List<CustomerDto> customerDtoList = new ArrayList<>();
        when(customerFasade.fetchCustomers()).thenReturn(customerDtoList);

        //When&Then
        mockMvc.perform(get("/v1/carworkshop/api/customers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));
    }

    @Test
    public void shouldFetchCustomersList() throws Exception {
        //Given
        List<CustomerDto> customerDtoList = new ArrayList<>();
        CustomerDto customer1 = new CustomerDto(1L,
                "Jan","Nowak",
                null,null,
                "122212141212",null,
                "kwisniowski@cxsa.pl","607241199",
                false,false,null);
        CustomerDto customer2 = new CustomerDto( 2L,
                "Alfred","X",
                null,null,
                "122212141212","232323",
                "kwisniowski@cxsa.pl", "199",
                true,true, null);
        customerDtoList.add(customer1);
        customerDtoList.add(customer2);
        when(customerFasade.fetchCustomers()).thenReturn(customerDtoList);

        //When&Then
        mockMvc.perform(get("/v1/carworkshop/api/customers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].lastname",is("Nowak")))
                .andExpect(jsonPath("$[1].phoneNumber",is("199")));
    }

    @Test
    public void shouldUpdateCustomer() throws Exception {
        //Given
        CustomerDto customer1 = new CustomerDto(1L,
                "Jan","Nowak",
                null,null,
                "122212141212",null,
                "kwisniowski@cxsa.pl","607241199", false, false, null);
        CustomerDto customerUpdated = new CustomerDto(1L,
                "Janek","Nowak",
                null,null,
                "122","32122112",
                "kwisniowski@cxsa.pl","607241199",
                false,false,null);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(customer1);
        when(customerFasade.updateCustomer(ArgumentMatchers.any(CustomerDto.class))).thenReturn(customerUpdated);

        //When&Then
        mockMvc.perform(put("/v1/carworkshop/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.firstname",is("Janek")))
                .andExpect(jsonPath("$.lastname",is("Nowak")));
    }

    @Test
    public void shouldCreateCustomer() throws Exception {
        //Given
        CustomerDto customer1 = new CustomerDto(1L,
                "Jan","Nowak",
                null,null,
                "122212141212",null,
                "kwisniowski@cxsa.pl","607241199",
                false,false,null);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(customer1);

        //When
        mockMvc.perform(post("/v1/carworkshop/api/customers")
                .characterEncoding("Utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());

        //Then
        verify(customerFasade,times(1)).createCustomer(any(CustomerDto.class));
    }

    @Test
    public void shouldDeleteCustomer() throws Exception {
        //Given
        //When
        mockMvc.perform(delete("/v1/carworkshop/api/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
        //Then
        verify(customerFasade,times(1)).deleteCustomer(any(Long.class));
    }

}
