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
@RequestMapping(value = "/login")
public class VendorLogin {
	private VendorServiceImp vendorService;

	@Autowired
	public VendorLogin(VendorServiceImp vendorService) {
		this.vendorService = vendorService;
	}

	@GetMapping
	public String reloadLoginForm() {
		return "html/LogInForm.html";
	}

	@PostMapping
	public String loginTask(@ModelAttribute Vendor vendor) {
		vendor.setEmail(vendor.getEmail());
		vendor.setPassword(vendor.getPassword());
		if (vendorService.selectVendor(vendor)) {
			return "VendorDashboard.jsp";
		}
		return "LoginFailure.jsp";
	}
}
