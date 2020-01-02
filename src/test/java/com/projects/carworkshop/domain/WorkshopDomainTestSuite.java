package com.projects.carworkshop.domain;

import org.h2.tools.Server;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class WorkshopDomainTestSuite {

    @BeforeClass
    public static void initTest() throws SQLException {
        Server webServer = Server.createWebServer("-web",
                "-webAllowOthers", "-webPort", "8082");
        webServer.start();
    }

    @Test
    public void testCalculateRentCost() {
        //Given
        Customer testCustomer = new Customer(
                "Jan","Nowak",
                null,null,
                "122212141212",null,
                "kwisniowski@cxsa.pl","607241199",
                false,false);
        RentalCar testRentalCar = new RentalCar(
                Car.CarBrand.CITROEN,"C3","2019",
                "WE112S122WEE4",1.6,"KR2323X",
                Car.CarBodyType.SEDAN);
        Rent testRent = new Rent(testRentalCar,10000,1.50,20);

        //When
        testRent.endRent(10200);
        double expectedCost = testRent.getRentCost();

        //Then
        Assert.assertEquals(320,expectedCost,0);
    }

}
