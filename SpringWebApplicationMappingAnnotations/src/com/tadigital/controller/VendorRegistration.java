package com.tadigital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tadigital.entity.Vendor;
import com.tadigital.service.VendorServiceImp;

@Controller
@RequestMapping(value = "/register")
public class VendorRegistration {

	private VendorServiceImp vendorService;

	@Autowired
	public VendorRegistration(VendorServiceImp vendorService) {
		this.vendorService = vendorService;
	}

	@GetMapping
	public String reloadRegisterForm() {
		return "html/RegistrationForm.html";
	}

	@PostMapping
	public String loginTask(@ModelAttribute Vendor vendor) {
		vendor.setName(vendor.getName());
		vendor.setEmail(vendor.getEmail());
		vendor.setPassword(vendor.getPassword());
		if (vendorService.insertVendor(vendor)) {
			return "RegistrationSuccess.jsp";
		}
		return "RegistrationFailure.jsp";
	}
}
