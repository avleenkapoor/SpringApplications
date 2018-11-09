package com.tadigital.service;

import java.util.List;

import com.tadigital.entity.Vendor;

public interface VendorServiceImp {
	
	public boolean insertVendor(Vendor vendor);
	public boolean selectVendor(Vendor vendor);
	public List<Vendor> selectAllUsers();
	public Vendor selectVendorById(int vid);
	void deleteVendor(int vid);
	
}
