package com.projects.carworkshop.mapper;

import com.projects.carworkshop.domain.*;
import com.projects.carworkshop.dto.RepairDto;
import com.projects.carworkshop.repository.*;
import org.h2.tools.Server;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RepairMapperTestSuite {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    SparePartRepository sparePartRepository;

    @Autowired
    RepairRepository repairRepository;
    @Autowired
    RepairMapper repairMapper;

    @BeforeClass
    public static void initTest() throws SQLException {
        Server webServer = Server.createWebServer("-web",
                "-webAllowOthers", "-webPort", "8100");
        webServer.start();
    }

    @Test
    public void shouldMapToRepairDto () {

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

        SparePart testSpare1 = new SparePart(Car.CarBrand.PEUGEOT, "206", "Bosh", 230);
        SparePart testSpare2 = new SparePart(Car.CarBrand.CITROEN, "C3", "Bosh", 199);


        customerRepository.save(testCustomer);
        carRepository.save(testCar1);
        sparePartRepository.save(testSpare1);
        sparePartRepository.save(testSpare2);

        Repair testRepair = new Repair(1L, testCar1, LocalDate.now(), LocalDate.now().plus(3, ChronoUnit.DAYS), 2680);

        repairRepository.save(testRepair);

        //When
        RepairDto expectedRepairDto = repairMapper.mapToRepairDto(testRepair);

        //Then
        Assert.assertEquals(LocalDate.now().plus(3,ChronoUnit.DAYS),expectedRepairDto.getEndDate());
        Assert.assertEquals(2680,expectedRepairDto.getTotalCost(),0);
        System.out.println();
    }

    @Test
    public void shouldMapToRepair () {

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

        SparePart testSpare1 = new SparePart(Car.CarBrand.PEUGEOT, "206", "Bosh", 230);
        SparePart testSpare2 = new SparePart(Car.CarBrand.CITROEN, "C3", "Bosh", 199);

        customerRepository.save(testCustomer);
        carRepository.save(testCar1);
        sparePartRepository.save(testSpare1);
        sparePartRepository.save(testSpare2);

        RepairDto testRepairDto = new RepairDto(1L, testCar1.getId(), LocalDate.now(), LocalDate.now().plus(3, ChronoUnit.DAYS), 2680);

        //When
        Repair expectedRepair = repairMapper.mapToRepair(testRepairDto);

        //Then
        Assert.assertEquals(2680,expectedRepair.getTotalCost(),0);
        Assert.assertNotNull(expectedRepair.getCar());

    }
}
