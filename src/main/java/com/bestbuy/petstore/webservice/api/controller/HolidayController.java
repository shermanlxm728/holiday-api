package com.bestbuy.petstore.webservice.api.controller;

import com.bestbuy.petstore.webservice.dto.HolidayDTO;
import com.bestbuy.petstore.webservice.model.Holiday;
import com.bestbuy.petstore.webservice.service.IHolidayService;
import com.bestbuy.petstore.webservice.validator.HolidayValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Valid
public class HolidayController {
    @Autowired
    @Qualifier("holidayService")
    private IHolidayService holidayService;


    @Autowired
    private HolidayValidator holidayValidator;

    @RequestMapping(method = RequestMethod.GET, path= "/holidays")
    public ResponseEntity<Object> getAllHolidays() {
        List<HolidayDTO> holidays = holidayService.getAllHolidays();
        return new ResponseEntity<>(holidays, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path= "/holiday")
    public ResponseEntity<Object> addHoliday(@Valid  @RequestBody HolidayDTO holiday){
        holidayValidator.validateHoliday(holiday);
        holidayService.addHoliday(holiday);
        return new ResponseEntity<>("Holiday create successfully.", HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, path= "/holiday/{id}")
    public ResponseEntity<Object> updateHoliday(@PathVariable String id, @Valid @RequestBody HolidayDTO holiday) {
        holidayValidator.validateHolidayAndHolidayId(id, holiday);
        int idInteger = Integer.parseInt(id);
        holidayService.updateHolidayById(idInteger, holiday);
        return new ResponseEntity<>(String.format("Holiday with id: %s updated.", id), HttpStatus.OK);
    }
}
