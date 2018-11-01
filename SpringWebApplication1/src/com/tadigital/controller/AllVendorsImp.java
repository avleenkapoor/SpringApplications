package com.tadigital.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tadigital.entity.Vendor;
import com.tadigital.service.VendorService;
import com.tadigital.service.VendorServiceImp;

@Controller
public class AllVendorsImp {
	VendorService vendorService;

	public AllVendorsImp(VendorService vendorService) {
		this.vendorService = vendorService;
	}

	@RequestMapping(value = "/allusers")
	public String allUsers(HttpServletRequest request) {
		ArrayList<Vendor> list = new ArrayList<>();
		list = vendorService.selectAllUsers();
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("VENDORLIST", list);
		return "VendorList.jsp";
	}
}