package com.tadigital.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String loginTask(HttpServletRequest request) {
		Vendor vendor = new Vendor();
		vendor.setName(request.getParameter("f1"));
		vendor.setEmail(request.getParameter("f2"));
		vendor.setPassword(request.getParameter("f3"));
		if (vendorService.insertVendor(vendor)) {
			return "RegistrationSuccess.jsp";
		}
		return "RegistrationFailure.jsp";
	}
}
