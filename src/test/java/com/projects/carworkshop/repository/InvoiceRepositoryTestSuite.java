package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.h2.tools.Server;

import java.sql.SQLException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InvoiceRepositoryTestSuite {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    InvoiceItemRepository invoiceItemRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    SparePartRepository sparePartRepository;
    @BeforeClass
    public static void initTest() throws SQLException {
        Server webServer = Server.createWebServer("-web",
                "-webAllowOthers", "-webPort", "8085");
        webServer.start();
    }

    @Test
    public void testInvoiceRepository() {

        //Given
        Customer testCustomer = new Customer(
                "Jan","Nowak",
                null,null,
                "122212141212",null,
                "kwisniowski@cxsa.pl","607241199",
                false,false);

        SparePart testSpare1 = new SparePart(Car.CarBrand.PEUGEOT,"206","Bosh",230);
        SparePart testSpare2 = new SparePart(Car.CarBrand.CITROEN,"C3","Bosh",199);

        InvoiceItem item1 = new InvoiceItem(testSpare1, 3);
        InvoiceItem item2 = new InvoiceItem(testSpare2, 10);

        Invoice invoice1 = new Invoice(testCustomer,24);

        invoice1.getItems().add(item1);
        invoice1.getItems().add(item2);
        item1.setInvoice(invoice1);
        item2.setInvoice(invoice1);

        //When
        customerRepository.save(testCustomer);
        sparePartRepository.save(testSpare1);
        sparePartRepository.save(testSpare2);
        invoiceRepository.save(invoice1);
        long expectedId = invoice1.getId();

        //Then
        Assert.assertNotEquals(0,expectedId);
    }
}
