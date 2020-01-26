package com.projects.carworkshop.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.projects.carworkshop.domain.ServiceCarRent;
import com.projects.carworkshop.dto.ServiceCarRentDto;
import com.projects.carworkshop.fasade.ServiceCarRentFasade;
import com.projects.carworkshop.service.ApplicationEventService;
import com.projects.carworkshop.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ServiceCarRentController.class)
@RunWith(SpringRunner.class)
public class ServiceCarRentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ServiceCarRentFasade fasade;

    @MockBean
    ApplicationEventService applicationEventService;

    @MockBean
    MailService mailService;

    @Test
    public void shouldFetchEmptyRentsList() throws Exception {
        //Given
        //When
        when(fasade.getAll()).thenReturn(new ArrayList<>());
        //Then
        mockMvc.perform(get("/v1/carworkshop/api/rents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));
    }

    @Test
    public void shouldFetchRentsList() throws Exception {
        //Given
        ServiceCarRentDto testRentDto = new ServiceCarRentDto(1L, LocalDate.now(), LocalDate.now().plus(1, ChronoUnit.DAYS));
        List<ServiceCarRentDto> testList = new ArrayList<>();
        testList.add(testRentDto);
        //When
        when(fasade.getAll()).thenReturn(testList);
        //Then
        mockMvc.perform(get("/v1/carworkshop/api/rents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)));
    }

    @Test
    public void shouldCreateServiceCarRent() throws Exception {
        //Given
        ServiceCarRentDto testRentDto = new ServiceCarRentDto(1L, LocalDate.now(), LocalDate.now().plus(1, ChronoUnit.DAYS));
        Gson gson = new Gson();
        String jsonContent = gson.toJson(testRentDto);
        String testContent = "{\"id\":250, \"startDate\": \"2020-01-01\", \"endDate\": \"2020-01-04\"}";

        //When
        mockMvc.perform(post("/v1/carworkshop/api/rents")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(testContent))
                .andExpect(status().isOk());

        //Then
        verify(fasade,times(1)).createServiceCarRentDto(any(ServiceCarRentDto.class));
    }
}
