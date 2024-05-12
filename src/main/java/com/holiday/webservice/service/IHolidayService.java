package com.holiday.webservice.service;

import com.holiday.webservice.dto.HolidayDTO;
import com.holiday.webservice.model.Holiday;

import java.util.List;

public interface IHolidayService {
    List<HolidayDTO> getAllHolidays();

    Holiday addHoliday(HolidayDTO holiday);

    Holiday updateHolidayById(int id, HolidayDTO holiday);
}
