package com.friendzone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HoneController {

	@GetMapping
	public String homeControllerHandler() {
		
		
		return "This is home";
	}
	@GetMapping("/home")
	public String homeControllerHandler2() {
		
		
		return "This is home 2";
	}
}

