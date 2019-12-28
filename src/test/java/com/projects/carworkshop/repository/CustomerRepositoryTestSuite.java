package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerRepositoryTestSuite {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void testCustomerSaveAndCount() {
        //Given
        Customer testCustomer = new Customer(
                "Jan","Nowak",
                null,null,
                "122212141212",null,
                "kwisniowski@cxsa.pl","607241199",
                false,false);

        //When
        customerRepository.save(testCustomer);

        //Then
        long expectedId = testCustomer.getId();
        Optional<Customer> expectedCustomer = customerRepository.findById(expectedId);
        long expectedCount = customerRepository.count();
        Assert.assertTrue(expectedCustomer.isPresent());
        Assert.assertEquals(1,expectedCount);

        //Clean-up
        customerRepository.deleteById(expectedId);
    }
}
