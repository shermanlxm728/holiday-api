package com.bestbuy.petstore.webservice.repository;

import com.bestbuy.petstore.webservice.model.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
public class CountryRepositoryTest {

    @Autowired
    CountryRepository countryRepository;

    @Test
    public void findByName(){
        Country country =countryRepository.findByName("Canada");
        assertNotNull(country);
    }
}