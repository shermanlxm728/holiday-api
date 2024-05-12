package com.bestbuy.petstore.webservice.repository;

import com.bestbuy.petstore.webservice.model.Holiday;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
public class HolidayRepositoryTest {

    @Autowired
    HolidayRepository holidayRepository;

    @Test
    public void findByName() {
        Holiday holiday = holidayRepository.findByName("Canada Day");
        assertNotNull(holiday);
    }

    @Test
    public void findAllHolidays(){
        List<Holiday> holidays = holidayRepository.findAllHolidays();
        assertEquals(2, holidays.size());
    }
}