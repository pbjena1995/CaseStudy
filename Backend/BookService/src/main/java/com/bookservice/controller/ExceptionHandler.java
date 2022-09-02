package com.bookservice.controller;

public class ExceptionHandler {
	
	public ExceptionHandler() {
		super();
	}

	
	public String handleExceptionForAll(Exception e) {
		return e.getMessage();
	}
}
