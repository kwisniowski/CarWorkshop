package com.projects.carworkshop.controller;

import com.google.gson.Gson;
import com.projects.carworkshop.domain.Car;
import com.projects.carworkshop.domain.Customer;
import com.projects.carworkshop.domain.Repair;
import com.projects.carworkshop.dto.CarDto;
import com.projects.carworkshop.dto.RepairDto;
import com.projects.carworkshop.fasade.CarFasade;
import com.projects.carworkshop.fasade.RepairFasade;
import com.projects.carworkshop.service.ApplicationEventService;
import com.projects.carworkshop.service.MailService;
import org.apache.tomcat.jni.Local;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RepairController.class)
public class RepiarControllerTest {

    @MockBean
    RepairFasade fasade;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ApplicationEventService applicationEventService;

    @MockBean
    MailService mailService;

    @Test
    public void shouldFetchAllRepiars() throws Exception {
        //Given
        RepairDto testRepairDto = new RepairDto(1L, 1L, LocalDate.now(), LocalDate.now(),1.0);
        List<RepairDto> testList = new ArrayList<>();
        testList.add(testRepairDto);
        when(fasade.fetchAllRepairs()).thenReturn(testList);

        //When
        mockMvc.perform(get("/v1/carworkshop/api/repairs")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)));
    }

    @Test
    public void shouldCreateRepair() throws Exception {
        //Given
        //When
        RepairDto testRepairDto = new RepairDto(1L, 1L, LocalDate.now(), LocalDate.now(),1.0);

        String jsonContent = "{\"id\":1, \"carId\":12,\"startDate\": \"2020-01-01\", \"endDate\": \"2020-01-02\", \"invoiceId\":1, \"totalCost\":1}";
        System.out.println(jsonContent);
        mockMvc.perform(post("/v1/carworkshop/api/repairs")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

        //Then
        verify(fasade,times(1)).createRepair(any(RepairDto.class));
    }
}
