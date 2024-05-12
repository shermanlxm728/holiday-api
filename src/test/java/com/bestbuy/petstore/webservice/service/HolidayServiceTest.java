package com.bestbuy.petstore.webservice.service;

import com.bestbuy.petstore.webservice.dto.HolidayDTO;
import com.bestbuy.petstore.webservice.exceptions.BadRequestException;
import com.bestbuy.petstore.webservice.exceptions.CountryNotFoundException;
import com.bestbuy.petstore.webservice.exceptions.HolidayNotFoundException;
import com.bestbuy.petstore.webservice.exceptions.SqlConnectionException;
import com.bestbuy.petstore.webservice.model.Country;
import com.bestbuy.petstore.webservice.model.Holiday;
import com.bestbuy.petstore.webservice.repository.CountryRepository;
import com.bestbuy.petstore.webservice.repository.HolidayRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class HolidayServiceTest {

    @Mock
    HolidayRepository holidayRepository;

    @Mock
    CountryRepository countryRepository;

    @InjectMocks
    HolidayService holidayService;

    private final int holidayId = 1;

    @BeforeEach
    void setUp(){

    }

    @Test
    void getAllHolidays() {
        //Given
        List<Holiday> returnHolidays = new ArrayList<>();

        String dateString = "2023-01-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);

        returnHolidays.add(Holiday.builder().id(1).name("New Year Day").date(date).country(Country.builder().name("United States").id(1l).code("USA").build()).build());

        //WHEN
        Mockito.when(holidayRepository.findAllHolidays()).thenReturn(returnHolidays);
        List<HolidayDTO> holidays = holidayService.getAllHolidays();
        //THEN
        Mockito.verify(holidayRepository, Mockito.times(1)).findAllHolidays();
        assertNotNull(holidays);
        assertEquals(holidays.size(), 1);
    }

    @Test
    void addHoliday() {
        //Given
        String dateString = "2023-01-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);

        Holiday returnHoliday = Holiday.builder().id(1).name("New Year Day").date(date).country(Country.builder().name("United States").id(1l).code("USA").build()).build();

        Country country = Country.builder().code("USA").id(1l).name("United States").build();

        //WHEN
        Mockito.when(holidayRepository.save(any())).thenReturn(returnHoliday);
        Mockito.when(countryRepository.findByName(any())).thenReturn(country);

        HolidayDTO holidayDTO = new HolidayDTO();
        holidayDTO.setDate(date);
        holidayDTO.setName("New Year Day");
        holidayDTO.setId(1);
        holidayDTO.setCountryName("United States");

        Holiday holiday = holidayService.addHoliday(holidayDTO);

        //THEN
        Mockito.verify(holidayRepository, Mockito.times(1)).save(any());
        assertNotNull(holiday);
        assertTrue(holiday.getId().equals(1));
        Assert.assertEquals(holiday.getName(), "New Year Day");
        Assert.assertEquals(holiday.getCountry().getName(), "United States");
        Assert.assertEquals(holiday.getDate(), date);
    }

    @Test
    void updateHolidayById() {
        //Given
        Country country = Country.builder().code("USA").id(1l).name("United States").build();
        String dateString = "2023-01-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);

        Holiday holidayToBeUpdate = Holiday.builder().country(country).date(date).name("New Year Day").id(1).build();

        Mockito.lenient().when(holidayRepository.findById(anyInt())).thenReturn(Optional.of(holidayToBeUpdate));
        //update price in this case to 30

        Mockito.when(holidayRepository.save(any())).thenReturn(holidayToBeUpdate);
        Mockito.when(countryRepository.findByName(any())).thenReturn(country);
        //WHEN

        //update name in this case
        HolidayDTO holidayDTO = new HolidayDTO();
        holidayDTO.setDate(date);
        holidayDTO.setName("New Year Holiday");
        holidayDTO.setId(1);
        holidayDTO.setCountryName("United States");

        Holiday holiday = holidayService.updateHolidayById(1, holidayDTO);

        assertNotNull(holiday);
        assertTrue(holiday.getId().equals(1));
        Assert.assertEquals(holiday.getName(), "New Year Holiday");
        Assert.assertEquals(holiday.getCountry().getName(), "United States");
        Assert.assertEquals(holiday.getDate(), date);
    }

    @Test
    void updateHolidayById_HolidayNotFoundException() {
        //Given
        Country country = Country.builder().code("USA").id(1l).name("United States").build();
        String dateString = "2023-01-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);

        Holiday holidayToBeUpdate = Holiday.builder().country(country).date(date).name("New Year Day").id(11).build();

        Mockito.lenient().when(holidayRepository.save(any())).thenReturn(holidayToBeUpdate);
        Mockito.lenient().when(countryRepository.findByName(any())).thenReturn(country);
        //WHEN

        //update name in this case
        HolidayDTO holidayDTO = new HolidayDTO();
        holidayDTO.setDate(date);
        holidayDTO.setName("New Year Holiday");
        holidayDTO.setId(1);
        holidayDTO.setCountryName("United States");

        assertThrows(HolidayNotFoundException.class, () -> holidayService.updateHolidayById(1, holidayDTO));

    }

    @Test
    void updateHolidayById_CountryNotFoundException() {
        //Given
        Country country = Country.builder().code("USA").id(1l).name("United States").build();
        String dateString = "2023-01-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);

        Holiday holidayToBeUpdate = Holiday.builder().country(country).date(date).name("New Year Day").id(1).build();

        Mockito.lenient().when(holidayRepository.findById(anyInt())).thenReturn(Optional.of(holidayToBeUpdate));
        //update price in this case to 30

        Mockito.lenient().when(holidayRepository.save(any())).thenReturn(holidayToBeUpdate);
        //WHEN

        //update name in this case
        HolidayDTO holidayDTO = new HolidayDTO();
        holidayDTO.setDate(date);
        holidayDTO.setName("New Year Holiday");
        holidayDTO.setId(1);
        holidayDTO.setCountryName("India");

        assertThrows(CountryNotFoundException.class, () -> holidayService.updateHolidayById(1, holidayDTO));
    }


    @Test
    void updateHolidayById_SqlException() {
        //Given
        Country country = Country.builder().code("USA").id(1l).name("United States").build();
        String dateString = "2023-01-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);

        Holiday holidayToBeUpdate = Holiday.builder().country(country).date(date).name("New Year Day").id(1).build();

        Mockito.lenient().when(holidayRepository.findById(anyInt())).thenThrow(SqlConnectionException.class);
        //update price in this case to 30

        Mockito.lenient().when(holidayRepository.save(any())).thenReturn(holidayToBeUpdate);
        Mockito.lenient().when(countryRepository.findByName(any())).thenReturn(country);
        //WHEN

        //update name in this case
        HolidayDTO holidayDTO = new HolidayDTO();
        holidayDTO.setDate(date);
        holidayDTO.setName("New Year Holiday");
        holidayDTO.setId(1);
        holidayDTO.setCountryName("United States");

        assertThrows(SqlConnectionException.class, () -> holidayService.updateHolidayById(1, holidayDTO));


    }
}