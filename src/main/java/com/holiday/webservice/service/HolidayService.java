package com.holiday.webservice.service;

import com.holiday.webservice.dto.HolidayDTO;
import com.holiday.webservice.exceptions.BadRequestException;
import com.holiday.webservice.exceptions.CountryNotFoundException;
import com.holiday.webservice.exceptions.HolidayNotFoundException;
import com.holiday.webservice.exceptions.SqlConnectionException;
import com.holiday.webservice.model.Country;
import com.holiday.webservice.model.Holiday;
import com.holiday.webservice.repository.CountryRepository;
import com.holiday.webservice.repository.HolidayRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class HolidayService implements IHolidayService{

    @Autowired
    private HolidayRepository holidayRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<HolidayDTO> getAllHolidays() {
        try {
            List<Holiday> holidays = holidayRepository.findAllHolidays();
            return holidays.stream().map(this::convertToDTO) .collect(Collectors.toList());

        }
        catch(SqlConnectionException ex){
            throw new SqlConnectionException(String.format("Error on getAllHolidays with message %s. ", ex.getMessage()));
        }
    }

    private HolidayDTO convertToDTO(Holiday holiday) {
        HolidayDTO dto = new HolidayDTO();
        dto.setId(holiday.getId());
        dto.setName(holiday.getName());
        dto.setDate(holiday.getDate());
        dto.setCountryName(holiday.getCountry().getName());
        return dto;
    }

    @Override
    public Holiday addHoliday(HolidayDTO holidayDTO) {
        Holiday holidayInDB = holidayRepository.findByName(holidayDTO.getName());
        if(holidayInDB != null ) {
            throw new BadRequestException(String.format("Holiday with name and country: %s in database already exsit. ", holidayInDB.getName() + ", " + holidayInDB.getCountry()));
        }

        try {
            Holiday holidayEntity = convertToEntity(holidayDTO);
            return holidayRepository.save(holidayEntity);
        }
        catch(SqlConnectionException ex){
            throw new SqlConnectionException(String.format("Error on addHoliday with message %s. ", ex.getMessage()));
        }
    }

    private Holiday convertToEntity(HolidayDTO dto) {
        Holiday holiday = new Holiday();
        holiday.setId(dto.getId());
        holiday.setName(dto.getName());
        holiday.setDate(dto.getDate());

        Country country = countryRepository.findByName(dto.getCountryName());
        if(country == null) {
            throw new CountryNotFoundException(String.format("Not able to finding country. "));
        }
        else
            holiday.setCountry(country);

        return holiday;
    }

    @Override
    public Holiday updateHolidayById(int id, HolidayDTO holidayDTO) {
        Holiday holidayInDB = getHolidayById(id);
        if(holidayDTO.getName() != null) {
            holidayInDB.setName(holidayDTO.getName());
        }

        if(holidayDTO.getDate() != null) {
            holidayInDB.setDate(holidayDTO.getDate());

        }

        if(holidayDTO.getCountryName() != null) {
            Country country = countryRepository.findByName(holidayDTO.getCountryName());
            if(country == null) {
                throw new CountryNotFoundException(String.format("Not able to finding country. "));
            }
            else
                holidayInDB.setCountry(country);
        }

        try {
            return holidayRepository.save(holidayInDB);
        }
        catch(SqlConnectionException ex){
            throw new SqlConnectionException(String.format("Error on updatePetById with message %s. ", ex.getMessage()));
        }
    }



    private Holiday getHolidayById(int id) {
        try {
            Optional<Holiday> holiday = holidayRepository.findById(id);
            if (!holiday.isPresent()) {
                throw new HolidayNotFoundException(String.format("Holiday with id: %d not found.", id));
            }
            else {
                return holiday.get();
            }
        }
        catch(SqlConnectionException ex){
            throw new SqlConnectionException(String.format("Error on getHolidayById with message %s. ", ex.getMessage()));
        }
    }
}
