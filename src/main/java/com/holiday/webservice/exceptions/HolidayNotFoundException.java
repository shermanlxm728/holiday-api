package com.holiday.webservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HolidayNotFoundException extends RuntimeException{
    public HolidayNotFoundException(String message) {
        super(message);
    }
}
