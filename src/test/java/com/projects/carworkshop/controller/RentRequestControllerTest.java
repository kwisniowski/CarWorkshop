package com.projects.carworkshop.controller;


import com.google.gson.Gson;
import com.projects.carworkshop.config.MailConfig;
import com.projects.carworkshop.controller.RentRequestController;
import com.projects.carworkshop.domain.RentRequest;
import com.projects.carworkshop.dto.RentRequestDto;
import com.projects.carworkshop.fasade.RentRequestFasade;
import com.projects.carworkshop.service.MailService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RentRequestController.class)
@RunWith(SpringRunner.class)
public class RentRequestControllerTest {

    @MockBean
    RentRequestFasade fasade;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MailService mailService;

    @MockBean
    MailConfig mailConfig;

    @Test
    public void shouldFetchEmptyRentsList() throws Exception {
        //Given
        when(fasade.fetchAllRentRequests()).thenReturn(new ArrayList<>());
        //When
        mockMvc.perform(get("/v1/carworkshop/api/rentRequests").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));
    }

    @Test
    public void shouldFetchRentsList() throws Exception {
        //Given
        RentRequestDto testRequest = new RentRequestDto(1l,"kwisniowski", LocalDate.now(), LocalDate.now().plus(1, ChronoUnit.DAYS));
        List<RentRequestDto> testList = new ArrayList<>();
        testList.add(testRequest);
        when(fasade.fetchAllRentRequests()).thenReturn(testList);
        //When
        mockMvc.perform(get("/v1/carworkshop/api/rentRequests").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].customerName", is("kwisniowski")));
    }

    @Test
    public void shouldReturnRentRequestsCount() throws Exception {
        //Given
        //When
        RentRequestDto testRequest = new RentRequestDto(1l,"kwisniowski", LocalDate.now(), LocalDate.now().plus(1, ChronoUnit.DAYS));
        List<RentRequestDto> testList = new ArrayList<>();
        testList.add(testRequest);
        when(fasade.countRequests()).thenReturn(1L);

        //Then
        mockMvc.perform(get("/v1/carworkshop/api/rentRequests/count")
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",is(1)));
    }

    @Test
    public void shouldDeleteRentRequests() throws Exception {
        //Given
        //When
        mockMvc.perform(delete("/v1/carworkshop/api/rentRequests/1")
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
        //Then
        verify(fasade,times(1)).deleteRequest(any(Long.class));
    }

    @Test
    public void shouldCreateRentRequest() throws Exception {
        //Given
        String jsonContent = "{\"id\":1, \"customerName\":\"kwisniowski\",\"startDate\": \"2020-01-01\", \"endDate\": \"2020-01-02\"}";
        System.out.println(jsonContent);
        //When
        mockMvc.perform(post("/v1/carworkshop/api/rentRequests")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

        //Then
        verify(fasade,times(1)).createRentRequest(any(RentRequestDto.class));
    }

}
