package com.author.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Test {
	
	
	@GetMapping("/text")
	public String getText() {
		return "Jena";
	}

}
