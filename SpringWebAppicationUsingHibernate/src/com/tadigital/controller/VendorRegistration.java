package com.tadigital.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tadigital.entity.Vendor;
import com.tadigital.service.VendorService;

@Controller
@RequestMapping(value="/register")
public class VendorRegistration {

	private VendorService vendorService;

	@Autowired
	public VendorRegistration(VendorService vendorService) {
		this.vendorService = vendorService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public String reloadRegisterForm() {
		return "html/RegistrationForm.html";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String registerTask(@ModelAttribute Vendor vendor, @RequestParam Part image ) {
		vendor.setPart(image);
		if (vendorService.insertVendor(vendor)) {
			return "RegistrationSuccess.jsp";
		}
		return "RegistrationFailure.jsp";
	}
}


