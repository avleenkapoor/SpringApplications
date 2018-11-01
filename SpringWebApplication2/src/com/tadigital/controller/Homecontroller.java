package com.tadigital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Homecontroller {
	@RequestMapping(value = { "/", "/home" })
	public String reloadHome() {
		return "index.jsp";
	}

	@RequestMapping(value = "/registerform")
	public String reloadRegisterForm() {
		return "RegistrationForm.jsp";
	}

	@RequestMapping(value = "/loginform")
	public String reloadLoginForm() {
		return "LogInForm.jsp";
	}
}
