package com.projects.carworkshop.mapper;

import com.projects.carworkshop.domain.Car;
import com.projects.carworkshop.domain.Customer;
import com.projects.carworkshop.dto.CarDto;
import com.projects.carworkshop.dto.CustomerDto;
import com.projects.carworkshop.service.CustomerService;
import org.h2.tools.Server;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.sql.SQLException;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarMapperTestSuite {

    @Autowired
    CarMapper carMapper;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    CustomerService customerService;

    @BeforeClass
    public static void initTest() throws SQLException {
        Server webServer = Server.createWebServer("-web",
                "-webAllowOthers", "-webPort", "8095");
        webServer.start();
    }

    @Test
    public void shouldMapToCarDto() {
        //Given
        Customer testCustomer = new Customer(1l,
                "Jan", "Nowak",
                null, null,
                "122212141212", null,
                "kwisniowski@cxsa.pl", "607241199",
                false, false, new ArrayList<>(), new ArrayList<>());
        Car testCar1 = new Car(
                Car.CarBrand.CITROEN, "C3", "2018", "12121TETTTEWBBW",
                1.9, "kr3043l", Car.CarBodyType.CABRIO, testCustomer);
        Car testCar2 = new Car(
                Car.CarBrand.CITROEN, "C5", "2017", "EXX88XXX9C121",
                1.9, "DW2322H", Car.CarBodyType.CABRIO, testCustomer);

        CarDto expectedDto = carMapper.mapToCarDto(testCar1);

        //When
        String expectedDtoModel = expectedDto.getModel();
        long expectedDtoCustomerId = expectedDto.getCustomerId();
        //Then
        Assert.assertEquals("C3", expectedDtoModel);
        Assert.assertEquals(1, expectedDtoCustomerId);
    }

    @Test
    public void shouldMapToCar() {
        //Given
        Customer testCustomer = new Customer(1l,
                "Jan", "Nowak",
                null, null,
                "122212141212", null,
                "kwisniowski@cxsa.pl", "607241199",
                false, false, new ArrayList<>(), new ArrayList<>());
        customerService.save(testCustomer);
        CarDto testCarDto = new CarDto(1L, Car.CarBrand.CITROEN, "C3", "2018", "12121TETTTEWBBW",
                1.9, "kr3043l", Car.CarBodyType.CABRIO, 1,new ArrayList<>());
        Car expectedCar = carMapper.mapToCar(testCarDto);

        //When
        String expectedModel = expectedCar.getModel();
        //Then
        Assert.assertTrue(expectedCar.getOwner()!=null);
    }
}
