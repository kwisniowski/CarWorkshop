package com.projects.carworkshop.mapper;

import com.projects.carworkshop.domain.Car;
import com.projects.carworkshop.domain.Customer;
import com.projects.carworkshop.dto.CustomerDto;
import org.h2.tools.Server;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CutomerMapperTestSuite {

    @Autowired
    CustomerMapper mapper;

    @BeforeClass
    public static void initTest() throws SQLException {
        Server webServer = Server.createWebServer("-web",
                "-webAllowOthers", "-webPort", "8102");
        webServer.start();
    }

    @Test
    public void shouldMapToCustomerDto() {
        //Given
        Customer testCustomer = new Customer(1l,
                "Jan", "Nowak",
                null, null,
                "122212141212", null,
                "kwisniowski@cxsa.pl", "607241199",
                false, false, new ArrayList<>(), new ArrayList<>());

        //When
        CustomerDto expectedDto = mapper.mapToCustomerDto(testCustomer);
        //Then
        System.out.println();
        Assert.assertFalse(expectedDto.isCompanyCustomer());
        Assert.assertEquals("Nowak", expectedDto.getLastname());
    }

    @Test
    public void shouldMapToCustomer() {
        //Given
        CustomerDto testCustomerDto = new CustomerDto(1l,
                "Jan", "Nowak",
                null, null,
                "122212141212", null,
                "kwisniowski@cxsa.pl", "607241199",
                false, false, new ArrayList<>(), new ArrayList<>());

        //When
        Customer expectedCustomer = mapper.mapToCustomer(testCustomerDto);
        //Then
        System.out.println();
        Assert.assertFalse(expectedCustomer.isCompanyCustomer());
        Assert.assertEquals("Nowak", expectedCustomer.getLastname());
    }

}
