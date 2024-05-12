package com.bestbuy.petstore.webservice.service;

import com.bestbuy.petstore.webservice.dto.HolidayDTO;
import com.bestbuy.petstore.webservice.model.Holiday;

import java.util.List;

public interface IHolidayService {
    List<HolidayDTO> getAllHolidays();

    Holiday addHoliday(HolidayDTO holiday);

    Holiday updateHolidayById(int id, HolidayDTO holiday);
}
