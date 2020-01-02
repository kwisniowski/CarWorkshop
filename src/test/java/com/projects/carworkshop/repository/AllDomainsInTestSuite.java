package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.*;
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

@SpringBootTest
@RunWith(SpringRunner.class)
public class AllDomainsInTestSuite {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    InvoiceItemRepository invoiceItemRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    SparePartRepository sparePartRepository;
    @Autowired
    RepairRepository repairRepository;
    @Autowired
    CarRepository carRepository;

    @BeforeClass
    public static void initTest() throws SQLException {
        Server webServer = Server.createWebServer("-web",
                "-webAllowOthers", "-webPort", "8084");
        webServer.start();
    }

    @Test
    public void testCalculateRepairValue() {

        //Given
        Customer testCustomer = new Customer(
                "Jan", "Nowak",
                null, null,
                "122212141212", null,
                "kwisniowski@cxsa.pl", "607241199",
                false, false);

        Car testCar = new Car(
                Car.CarBrand.CITROEN, "C3", "2018", "12121TETTTEWBBW",
                1.9, "kr3043l", Car.CarBodyType.CABRIO, testCustomer);

        SparePart testSpare1 = new SparePart(Car.CarBrand.PEUGEOT, "206", "Bosh", 200);
        SparePart testSpare2 = new SparePart(Car.CarBrand.CITROEN, "C3", "Bosh", 100);

        Repair testRepair = new Repair(testCar, LocalDate.now());
        InvoiceItem item1 = new InvoiceItem(testSpare1, 1);
        InvoiceItem item2 = new InvoiceItem(testSpare2, 2);
        Invoice invoice1 = new Invoice(testCustomer, 7);

        //When
        invoice1.getItems().add(item1);
        invoice1.getItems().add(item2);
        item1.setInvoice(invoice1);
        item2.setInvoice(invoice1);
        testRepair.setInvoice(invoice1);

        double expectedValue = testRepair.getTotalCost();

        customerRepository.save(testCustomer);
        carRepository.save(testCar);
        sparePartRepository.save(testSpare1);
        sparePartRepository.save(testSpare2);
        repairRepository.save(testRepair);
        invoiceItemRepository.save(item1);
        invoiceItemRepository.save(item2);
        invoiceRepository.save(invoice1);

        //Then
        Assert.assertEquals(400,expectedValue,0);
        while(true);
    }


}
