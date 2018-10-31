package com.tadigital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Homecontroller {
			@RequestMapping(value="/")
			public String reloadHome() {
				return "index.jsp";
			
		}
}
