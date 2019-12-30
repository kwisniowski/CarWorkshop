package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.Car;
import com.projects.carworkshop.domain.Customer;
import com.projects.carworkshop.domain.Repair;
import com.projects.carworkshop.domain.SparePart;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RepairRepositoryTestSuite {

    @Autowired
    RepairRepository repairRepository;

    @Test
    public void repairSaveTestSuite() {

        //Given
        Customer testCustomer = new Customer(
                "Jan", "Nowak",
                null, null,
                "122212141212", null,
                "kwisniowski@cxsa.pl", "607241199",
                false, false);
        Car testCar1 = new Car(
                Car.CarBrand.CITROEN, "C3", "2018", "12121TETTTEWBBW",
                1.9, "kr3043l", Car.CarBodyType.CABRIO, testCustomer);

        SparePart testSpare1 = new SparePart(Car.CarBrand.PEUGEOT,"C3","Bosh",230);
        SparePart testSpare2 = new SparePart(Car.CarBrand.PEUGEOT,"CX","RW",11);
        Repair testRepair1 = new Repair(testCar1, LocalDate.now());

        testRepair1.getSpareParts().put(testSpare1,1);
        testRepair1.getSpareParts().put(testSpare2,1);

        //When
        repairRepository.save(testRepair1);
        long expectedId = testRepair1.getId();

        //Then
        Assert.assertNotEquals(0,expectedId);
    }
}
