package com.tadigital.service;

import java.util.ArrayList;

import com.tadigital.entity.Vendor;

public interface VendorService {
	
	public boolean insertVendor(Vendor vendor);
	public boolean selectVendor(Vendor vendor);
	public ArrayList<Vendor> selectAllUsers();
	
}
