package com.tadigital.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tadigital.entity.Vendor;
import com.tadigital.service.VendorServiceImp;

@Controller
public class VendorRegistration {

	private VendorServiceImp vendorService;

	@Autowired
	public VendorRegistration(VendorServiceImp vendorService) {
		this.vendorService = vendorService;
	}

	@RequestMapping(value = "/register")
	public String loginTask(@ModelAttribute Vendor vendor, HttpServletRequest request) {
		vendor.setName(vendor.getName());
		vendor.setEmail(vendor.getEmail());
		vendor.setPassword(vendor.getPassword());
		if (vendorService.insertVendor(vendor)) {
			return "RegistrationSuccess.jsp";
		}
		return "RegistrationFailure.jsp";
	}
}
