package com.tadigital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Homecontroller {
	@RequestMapping(value = { "/", "/home" })
	public String reloadHome() {
		return "html/index.html";
	}

	@RequestMapping(value = "/registerform")
	public String reloadRegisterForm() {
		return "html/RegistrationForm.html";
	}

	@RequestMapping(value = "/loginform")
	public String reloadLoginForm() {
		return "html/LogInForm.html";
	}
}
