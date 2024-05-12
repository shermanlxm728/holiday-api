package com.holiday.webservice.utils;

import java.time.format.DateTimeParseException;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.holiday.webservice.exceptions.BadRequestException;
import com.holiday.webservice.exceptions.ExceptionResponse;
import com.holiday.webservice.exceptions.SqlConnectionException;



@ControllerAdvice
@RestController
public class ExceptionHandler extends ResponseEntityExceptionHandler{
	
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(),
				request.getDescription(false), "ERROR", null);
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<Object> handleBadRequestException(BadRequestException exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
				request.getDescription(false), "ERROR", exception.getMessage());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler(SqlConnectionException.class)
	public final ResponseEntity<Object> handleSqlConnectionException(SqlConnectionException exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Sql Connection Error",
				request.getDescription(false), "ERROR", exception.getMessage());
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<String> handleInvalidDateException(DateTimeParseException exception, WebRequest request) {
		// Log the exception or perform any other necessary actions
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Date Format Error",
				request.getDescription(false), "ERROR", exception.getMessage());

		// Return an error response to the client
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date format: " + exception.getMessage());
	}
}
