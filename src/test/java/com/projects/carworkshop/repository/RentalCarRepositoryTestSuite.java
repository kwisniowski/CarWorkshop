package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.Car;
import com.projects.carworkshop.domain.Customer;
import com.projects.carworkshop.domain.RentalCar;
import org.h2.tools.Server;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RentalCarRepositoryTestSuite {

    @Autowired
    RentalCarRepository rentalCarRepository;

    @BeforeClass
    public static void initTest() throws SQLException {
        Server webServer = Server.createWebServer("-web",
                "-webAllowOthers", "-webPort", "8086");
        webServer.start();
    }

    @Test
    public void testRentalCarSave() {
        //Given
        RentalCar testCar1 = new RentalCar(
                Car.CarBrand.CITROEN, "C3", "1990", "SDSQ1231EAD12",
                1.9, "KR3232P", Car.CarBodyType.SEDAN);

        //When
        rentalCarRepository.save(testCar1);

        //Then
        long testId = testCar1.getId();
        Optional<RentalCar> expectedRentalCar = rentalCarRepository.findById(testId);
        Assert.assertTrue(expectedRentalCar.isPresent());
    }
}