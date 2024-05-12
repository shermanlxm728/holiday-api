package com.bestbuy.petstore.webservice.repository;

import com.bestbuy.petstore.webservice.model.Country;
import com.bestbuy.petstore.webservice.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Integer> {

    @Query(value = "SELECT * FROM holiday LIMIT 100", nativeQuery = true)
    List<Holiday> findAllHolidays();

    Holiday findByName(String name);
}
