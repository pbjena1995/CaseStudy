package com.author.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseExceptionHandler {

	public BaseExceptionHandler() {
		super();
	}
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> methodArgumentException(MethodArgumentNotValidException ex) {
		Map<String, String> error = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach(e -> {
			String fieldName = ((FieldError) e).getField();
			String message = ((FieldError) e).getDefaultMessage();
			error.put(fieldName, message);
		});
		return error;
	}
	@ExceptionHandler(Exception.class)
	public String baseExceptionHandle(Exception e) {
		return e.getMessage();
		}
}
