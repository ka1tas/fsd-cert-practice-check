package com.cts.signup.rest;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cts.signup.bean.ErrorResponse;

public class ExceptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleError(Exception ex) {
		LOGGER.info("start");
		LOGGER.error(ex.getMessage(), ex);
		ErrorResponse error = new ErrorResponse();
		error.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
		LOGGER.debug("error : {} ", error);
		ResponseEntity<ErrorResponse> response = null;
		if (ex instanceof ConstraintViolationException) {
			error.setReasonCode(HttpStatus.BAD_REQUEST.value());
			ConstraintViolationException constraintException = (ConstraintViolationException) ex;
			Set<ConstraintViolation<?>> set = constraintException.getConstraintViolations();
			String errorMessage = "Input Validation Failed:";
			for (ConstraintViolation<?> constraintViolation : set) {
				errorMessage += constraintViolation.getMessageTemplate() + ",";
			}
			errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
			error.setErrorMessage(errorMessage);
			response = new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		} else {
			error.setReasonCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			error.setErrorMessage(ex.getMessage());
			response = new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}
