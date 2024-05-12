package com.holiday.webservice.validator;

import com.holiday.webservice.dto.HolidayDTO;
import com.holiday.webservice.exceptions.BadRequestException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@Component
public class HolidayValidator {
    public void validateHoliday(HolidayDTO holiday) {
        if (StringUtils.isEmpty(holiday.getName())) {
            throw new BadRequestException("Holiday name value could not miss and value can not be null or empty");
        }
        if (StringUtils.isEmpty(holiday.getCountryName())) {
            throw new BadRequestException("Holiday country value could not miss and value can not be null or empty");
        }
        if (holiday.getDate() == null) {
            throw new BadRequestException("Holiday date could not miss");
        }
    }

    private boolean isValidId(String id) {
        Pattern pattern = Pattern.compile("^[1-9]\\d*$");
        return (id != null && pattern.matcher(id).matches());
    }

    public void validateHolidayId(String id) {
        if(!isValidId(id)) {
            throw new BadRequestException("Id format is not correct");
        }
    }

    public void validateHolidayAndHolidayId(String id, HolidayDTO holiday) {
        validateHolidayId(id);
        validateHoliday(holiday);
    }
}

