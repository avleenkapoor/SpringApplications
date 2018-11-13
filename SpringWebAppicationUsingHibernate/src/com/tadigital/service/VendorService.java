package com.tadigital.service;

import java.util.List;

import javax.servlet.http.Part;

import com.tadigital.entity.Vendor;

public interface VendorService {
	
	public boolean insertVendor(Vendor vendor);
	public boolean selectVendor(Vendor vendor);
	public List<Vendor> selectAllUsers();
	public Vendor selectVendorById(int vid);
	void deleteVendor(int vid);
	String getFileName(Part part);
	void editVendor(int vid, String name, String email);
	
}
