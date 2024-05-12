package com.bestbuy.petstore.webservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HolidayDTO {

    private Integer id;

    private String name;

    private LocalDate date;

    private String countryName;
}
