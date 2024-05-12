package com.bestbuy.petstore.webservice.repository;

import com.bestbuy.petstore.webservice.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findByName(String name);
}

