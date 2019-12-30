package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.Car;
import com.projects.carworkshop.domain.Rent;
import com.projects.carworkshop.domain.RentalCar;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RentRepositoryTestSuite {

    @Autowired
    RentRepository rentRepository;
    @Autowired
    RentalCarRepository rentalCarRepository;

    @Test
    public void testRentRepositorySave() {

        //Given
        RentalCar testCar1 = new RentalCar(
                Car.CarBrand.CITROEN,"C3","1990","SDSQ1231EAD12",
                1.9,"KR3232P", Car.CarBodyType.SEDAN);

        Rent testRent1 = new Rent(testCar1, 120000, 1.50, 100);
        testCar1.getRents().add(testRent1);

        //When
        rentalCarRepository.save(testCar1);
        rentRepository.save(testRent1);


        //Then
        long expectedId = testRent1.getId();
        Optional<Rent> expectedRent = rentRepository.findById(expectedId);
        Assert.assertNotNull(expectedRent.isPresent());

        //Clean-Up

        rentRepository.deleteById(testRent1.getId());

    }
}