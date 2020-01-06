package com.projects.carworkshop.controller;

import com.google.gson.Gson;
import com.projects.carworkshop.domain.Car;
import com.projects.carworkshop.domain.Customer;
import com.projects.carworkshop.dto.CarDto;
import com.projects.carworkshop.fasade.CarFasade;
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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
@RunWith(SpringRunner.class)
public class CarControllerTest {

    @MockBean
    CarFasade carFasade;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldFetchAllCars() throws Exception {
        //Given
        Customer testCustomer = new Customer(
                "Jan", "Nowak",
                null, null,
                "122212141212", null,
                "kwisniowski@cxsa.pl", "607241199",
                false, false);
        CarDto testCar1 = new CarDto(1L,
                Car.CarBrand.CITROEN, "C3", "2018", "12121TETTTEWBBW",
                1.9, "kr3043l", Car.CarBodyType.CABRIO, testCustomer, new ArrayList<>());
        List<CarDto> carList = new ArrayList<>();
        carList.add(testCar1);
        when(carFasade.fetchAllCars()).thenReturn(carList);

        //When
        mockMvc.perform(get("/v1/carworkshop/api/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)));
    }

    @Test
    public void sholdFetchEmptyCarList() throws Exception {
        //Given
        when(carFasade.fetchAllCars()).thenReturn(new ArrayList<>());

        //When
        mockMvc.perform(get("/v1/carworkshop/api/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));
    }

    @Test
    public void shouldDeleteCar() throws Exception {
        //Given
        //When
        mockMvc.perform(delete("/v1/carworkshop/api/cars/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //Then
        verify(carFasade,times(1)).deleteCar(any(Long.class));
    }

    @Test
    public void shouldCreateCar() throws Exception {
        //Given
        //When
        Customer testCustomer = new Customer(
                "Jan", "Nowak",
                null, null,
                "122212141212", null,
                "kwisniowski@cxsa.pl", "607241199",
                false, false);
        CarDto testCar1 = new CarDto(1L,
                Car.CarBrand.CITROEN, "C3", "2018", "12121TETTTEWBBW",
                1.9, "kr3043l", Car.CarBodyType.CABRIO, testCustomer, new ArrayList<>());
        Gson gson = new Gson();
        String jsonContent = gson.toJson(testCar1);

        mockMvc.perform(post("/v1/carworkshop/api/cars")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(carFasade,times(1)).createCar(any(CarDto.class));
    }

    @Test
    public void shouldUpdateCar() throws Exception {
        //Given
        Customer testCustomer = new Customer(
                "Jan", "Nowak",
                null, null,
                "122212141212", null,
                "kwisniowski@cxsa.pl", "607241199",
                false, false);
        CarDto testCar1 = new CarDto(1L,
                Car.CarBrand.CITROEN, "C3", "2018", "12121TETTTEWBBW",
                1.9, "kr3043l", Car.CarBodyType.CABRIO, testCustomer, new ArrayList<>());
        CarDto testCarUpdated = new CarDto(1L,
                Car.CarBrand.CITROEN, "C15", "2015", "12121TETTTEWBBW",
                1.0, "kr3043l", Car.CarBodyType.CABRIO, testCustomer, new ArrayList<>());
        Gson gson = new Gson();
        String jsonContent = gson.toJson(testCarUpdated);
        when(carFasade.updateCar(any(CarDto.class))).thenReturn(testCarUpdated);

        //When
        mockMvc.perform(put("/v1/carworkshop/api/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model",is("C15")))
                .andExpect(jsonPath("$.owner.firstname",is("Jan")));
    }
}


