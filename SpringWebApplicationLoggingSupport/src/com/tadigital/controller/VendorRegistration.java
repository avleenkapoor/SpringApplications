package com.tadigital.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.tadigital.entity.Vendor;
import com.tadigital.service.VendorServiceImp;

@Controller
@RequestMapping(value = "/register")
public class VendorRegistration {
	private static final Logger LOGGER = Logger.getLogger("VendorRegistration.class");
	private VendorServiceImp vendorService;

	@Autowired
	public VendorRegistration(VendorServiceImp vendorService) {
		LOGGER.info("VendorRegistration() controller Executed");
		this.vendorService = vendorService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String reloadRegisterForm() {
		LOGGER.info("reloadRegisterForm() controller Executed");
		return "html/RegistrationForm.html";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerTask(@ModelAttribute Vendor vendor) {
		LOGGER.info("registertask() Executed");
		vendor.setName(vendor.getName());
		vendor.setEmail(vendor.getEmail());
		vendor.setPassword(vendor.getPassword());
		if (vendorService.insertVendor(vendor)) {
			LOGGER.info("registerTask() Terminated");
			return "RegistrationSuccess.jsp";
		}
		LOGGER.info("registerTask() Terminated");
		return "RegistrationFailure.jsp";
	}
}
