package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.Car;
import com.projects.carworkshop.domain.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CarRepositoryTestSuite {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CarRepository carRepository;
    @Test
    public void testCarCustomerOneToManyRelation() {
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
        Car testCar2 = new Car(
                Car.CarBrand.CITROEN, "C5", "2017", "EXX88XXX9C121",
                1.9, "DW2322H", Car.CarBodyType.CABRIO, testCustomer);
        testCustomer.getCars().add(testCar1);
        testCustomer.getCars().add(testCar2);
        testCar1.setOwner(testCustomer);
        testCar2.setOwner(testCustomer);

        //When & Then
        customerRepository.save(testCustomer);
        carRepository.save(testCar1);
        carRepository.save(testCar2);

        long testId = testCustomer.getId();
        Assert.assertNotEquals(0,testId);

        //Clean-Up
        customerRepository.deleteById(testCustomer.getId());
    }
}
