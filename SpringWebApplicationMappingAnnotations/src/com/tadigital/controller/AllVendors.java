package com.tadigital.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tadigital.entity.Vendor;
import com.tadigital.service.VendorService;
import com.tadigital.service.VendorServiceImp;

@Controller
public class AllVendors {
	VendorService vendorService;
	ArrayList<Vendor> list = new ArrayList<>();

	public AllVendors(VendorService vendorService) {
		this.vendorService = vendorService;
	}

	@GetMapping(value = "/edit{vid}")
	public String editVendor(@PathVariable int vid, HttpSession session) {
		Vendor vendor = vendorService.selectVendorById(vid);
		session.setAttribute("VENDORTOBEEDITED", vendor);
		return "EditForm.jsp";
	}

	@PostMapping(value = "/editvendor{vid}")
	public String editVendor1(@PathVariable int vid, HttpServletRequest req,
		HttpSession session) {
		Vendor vendor = new Vendor();
		vendor.setId(vid);
		vendorService.editVendor(vid,req.getParameter("name"),req.getParameter("email"));
		System.out.println(req.getParameter("name"));
		list = (ArrayList<Vendor>) vendorService.selectAllUsers();
		session.setAttribute("VENDORLIST", list);
		return "VendorList.jsp";
	}
	
	@GetMapping(value = "/delete{vid}")
	public String editVendor1(@PathVariable int vid,HttpSession session) {
  	    vendorService.deleteVendor(vid);
		list = (ArrayList<Vendor>) vendorService.selectAllUsers();
		session.setAttribute("VENDORLIST", list);
		return "VendorList.jsp";
	}
	

	@RequestMapping(value = "/allusers")
	public String allUsers(HttpSession session) {
		list = (ArrayList<Vendor>) vendorService.selectAllUsers();
		session.setAttribute("VENDORLIST", list);
		return "VendorList.jsp";
	}
}