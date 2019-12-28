package com.projects.carworkshop.domain;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class WorkshopDomainTestSuite {

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
                Car.CarBodyType.SEDAN,testCustomer);
        Rent testRent = new Rent(testRentalCar,10000,1.50,20);

        //When
        testRent.endRent(10200);
        double expectedCost = testRent.getRentCost();
        //Then
        Assert.assertEquals(320,expectedCost,0);
    }

    @Test
    public void testCalculateRepairCost() {
        //Given
        SparePart spare1 = new SparePart("Valeo","BS-21", Car.CarBrand.PEUGEOT,300);
        SparePart spare2 = new SparePart("Bosh","We12-BC", Car.CarBrand.PEUGEOT, 50);
        Map<SparePart,Integer> spares = new HashMap<>();
        spares.put(spare1,2);
        spares.put(spare2,2);
        Repair testRepair = new Repair(null,LocalDate.now().minus(3,ChronoUnit.DAYS));
        testRepair.setSpareParts(spares);
        //When
        testRepair.endRepair();
        double totalRepairCost = testRepair.getTotalCost();

        //Then
        Assert.assertEquals(700, totalRepairCost, 0 );
    }
}
