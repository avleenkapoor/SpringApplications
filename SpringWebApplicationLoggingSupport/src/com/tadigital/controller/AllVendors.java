package com.tadigital.controller;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.tadigital.entity.Vendor;
import com.tadigital.service.VendorService;

@Controller
public class AllVendors {
	private static final Logger LOGGER = Logger.getLogger("AllVendors.class");
	VendorService vendorService;
	ArrayList<Vendor> list = new ArrayList<>();

	public AllVendors(VendorService vendorService) {
		LOGGER.info("AllVendors(VendorService) Executed");
		this.vendorService = vendorService;
	}

	@RequestMapping(value = "/edit{vid}", method = RequestMethod.GET)
	public String editVendor(@PathVariable int vid, HttpSession session) {
		LOGGER.info("editvendor() Executed");
		Vendor vendor = vendorService.selectVendorById(vid);
		session.setAttribute("VENDORTOBEEDITED", vendor);
		LOGGER.info("editVendor() Terminated");
		return "EditForm.jsp";
	}

	@RequestMapping(value = "/editvendor{vid}", method = RequestMethod.POST)
	public String editVendor1(@PathVariable int vid, HttpServletRequest req, HttpSession session) {
		LOGGER.info("editVendor1() GET Executed");
		Vendor vendor = new Vendor();
		vendor.setId(vid);
		vendorService.editVendor(vid, req.getParameter("name"), req.getParameter("email"));
		System.out.println(req.getParameter("name"));
		list = (ArrayList<Vendor>) vendorService.selectAllUsers();
		session.setAttribute("VENDORLIST", list);
		LOGGER.info("editVendor1() Terminated");
		return "VendorList.jsp";
	}

	@RequestMapping(value = "/delete{vid}", method = RequestMethod.GET)
	public String editVendor1(@PathVariable int vid, HttpSession session) {
		LOGGER.info("editVendor1() POST Executed");
		vendorService.deleteVendor(vid);
		list = (ArrayList<Vendor>) vendorService.selectAllUsers();
		session.setAttribute("VENDORLIST", list);
		LOGGER.info("editVendor1() POST Terminated");
		return "VendorList.jsp";
	}

	@RequestMapping(value = "/allusers")
	public String allUsers(HttpSession session) {
		LOGGER.info("allUsers() Executed");
		list = (ArrayList<Vendor>) vendorService.selectAllUsers();
		session.setAttribute("VENDORLIST", list);
		LOGGER.info("allUsers ETerminated");
		return "VendorList.jsp";
	}
}