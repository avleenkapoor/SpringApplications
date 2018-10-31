package com.tadigital.dao;

import com.tadigital.entity.Vendor;

public interface VendorDao {
	public boolean insertVendor(Vendor vendor);
	public boolean selectvendor(Vendor vendor);
	public boolean selectAllVendors();

}


