package com.tadigital.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tadigital.entity.Vendor;
import com.tadigital.service.VendorServiceImp;

@Controller
public class VendorLogin {
	private VendorServiceImp vendorService;

	@Autowired
	public VendorLogin(VendorServiceImp vendorService) {
		this.vendorService = vendorService;
	}

	@RequestMapping(value = "/login")
	public String loginTask(@ModelAttribute Vendor vendor, HttpServletRequest request) {
		vendor.setEmail(vendor.getEmail());
		vendor.setPassword(vendor.getPassword());
		if (vendorService.selectVendor(vendor)) {
			return "VendorDashboard.jsp";
		}
		return "LoginFailure.jsp";
	}
}
