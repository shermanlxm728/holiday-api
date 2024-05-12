package com.bestbuy.petstore.webservice.api.controller;

import com.bestbuy.petstore.webservice.dto.HolidayDTO;
import com.bestbuy.petstore.webservice.model.Country;
import com.bestbuy.petstore.webservice.model.Holiday;
import com.bestbuy.petstore.webservice.service.IHolidayService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class HolidayControllerTest {
    @MockBean
    IHolidayService service;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    List<HolidayDTO> holidays;

    HolidayDTO holidayDTO;

    Holiday holiday;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        holidayDTO = new HolidayDTO();
        holidayDTO.setId(3);
        holidayDTO.setCountryName("China");
        holidayDTO.setName("Chinese New Year");

        String dateString = "2023-02-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        holidayDTO.setDate(date);

        Country country = Country.builder()
                .code("CHN")
                .id(3L)
                .name("China")
                .build();

        holiday = Holiday.builder()
                .id(holidayDTO.getId())
                .name(holidayDTO.getName())
                .country(country)
                .date(date)
                .build();


        holidays = new ArrayList<>();
        holidays.add(holidayDTO);
    }


    @Test
    public void getAllHolidays() throws Exception {
        Mockito.when(service.getAllHolidays()).thenReturn(holidays);
        MockHttpServletRequestBuilder requestBuilder = request(HttpMethod.GET, "/holidays");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Chinese New Year"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].countryName").value("China"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].date").value("2023-02-01"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(3));

        verify(service, times(1)).getAllHolidays();

    }

    @Test
    public void addHoliday() throws Exception {
        Mockito.when(service.addHoliday(holidayDTO)).thenReturn(holiday);

        MockHttpServletRequestBuilder requestBuilder = request(HttpMethod.POST, "/holiday")
                .content(objectMapper.writeValueAsString(holidayDTO))
                .contentType("application/json");
        mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful());

        verify(service, times(1)).addHoliday(ArgumentMatchers.any());
    }

    @Test
    public void updateHoliday() throws Exception {
        Mockito.when(service.updateHolidayById(3, holidayDTO)).thenReturn(holiday);

        MockHttpServletRequestBuilder requestBuilder = request(HttpMethod.PUT, "/holiday/3")
                .content(objectMapper.writeValueAsString(holidayDTO))
                .contentType("application/json");
        mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful());


        verify(service,times(1)).updateHolidayById(eq(3), any());
    }
}