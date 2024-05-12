package com.bestbuy.petstore.webservice.validator;

import com.bestbuy.petstore.webservice.dto.HolidayDTO;
import com.bestbuy.petstore.webservice.exceptions.BadRequestException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class HolidayValidatorTest {

    HolidayValidator holidayValidator;

    HolidayDTO holidayDTO;



    @Test
    public void ValidateHoliday_NullName(){
        holidayValidator = new HolidayValidator();

        holidayDTO = new HolidayDTO();
        holidayDTO.setCountryName("United States");
        holidayDTO.setId(1);
        holidayDTO.setName(null);
        holidayDTO.setDate(LocalDate.now());

        assertThrows(BadRequestException.class, () -> holidayValidator.validateHoliday(holidayDTO));

    }

    @Test
    public  void ValidateHoliday_NullCountry() {
        holidayValidator = new HolidayValidator();

        holidayDTO = new HolidayDTO();
        holidayDTO.setCountryName(null);
        holidayDTO.setId(1);
        holidayDTO.setName("New Year Day");
        holidayDTO.setDate(LocalDate.now());

        assertThrows(BadRequestException.class, () -> holidayValidator.validateHoliday(holidayDTO));
    }
}