package com.tadigital.controller;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Homecontroller {
	private static final Logger LOGGER = Logger.getLogger("HomeController.class");
	@RequestMapping(value = { "/", "/home" })
	public String reloadHome() {
		LOGGER.info("reLoad() executed");
		return "html/index.html";
	}
}
