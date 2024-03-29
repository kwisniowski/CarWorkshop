package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.Car;
import com.projects.carworkshop.domain.SparePart;
import org.h2.tools.Server;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SparePartRepositoryTestSuite {

    @Autowired
    SparePartRepository sparePartRepository;

    @BeforeClass
    public static void initTest() throws SQLException {
        Server webServer = Server.createWebServer("-web",
                "-webAllowOthers", "-webPort", "8088");
        webServer.start();
    }

    @Test
    public void sparePartSaveTest() {

        //Given
        SparePart testSpare1 = new SparePart(Car.CarBrand.PEUGEOT,"C3","Bosh",230);

        //When
        sparePartRepository.save(testSpare1);
        long expectedId = testSpare1.getId();

        //Then
        Assert.assertNotEquals(0,expectedId);

        //Clean-up
        sparePartRepository.deleteById(testSpare1.getId());
    }

    //@Test
    public void testManyToManyWithRepairs() {
        SparePart testSpare1 = new SparePart(Car.CarBrand.PEUGEOT,"C3","Bosh",230);
        SparePart testSpare2 = new SparePart(Car.CarBrand.CITROEN,"ZX","Bosh",50);
    }
}
