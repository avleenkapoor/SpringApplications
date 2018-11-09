package com.tadigital.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tadigital.entity.Vendor;
import com.tadigital.service.VendorServiceImp;

@Controller
@RequestMapping(value = "/login")
public class VendorLogin {
	private static final Logger LOGGER = Logger.getLogger("VendorLoginr.class");
	private VendorServiceImp vendorService;

	@Autowired
	public VendorLogin(VendorServiceImp vendorService) {
		LOGGER.info("VendorLogin() Executed");
		this.vendorService = vendorService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String reloadLoginForm() {
		LOGGER.info("reLoadLoginForm() Executed");
		return "html/LogInForm.html";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String loginTask(@ModelAttribute Vendor vendor) {
		LOGGER.info("loginTask() Executed");
		vendor.setEmail(vendor.getEmail());
		vendor.setPassword(vendor.getPassword());
		if (vendorService.selectVendor(vendor)) {
			LOGGER.info("loginTask() Terminated");
			return "VendorDashboard.jsp";
		}
		LOGGER.info("loginTask() Terminated");
		return "LoginFailure.jsp";
	}
}
