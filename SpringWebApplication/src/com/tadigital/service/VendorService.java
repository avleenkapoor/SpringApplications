package com.tadigital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadigital.dao.VendorDao;
import com.tadigital.dao.VendorDaoImpl;
import com.tadigital.entity.Vendor;

@Service
public class VendorService implements VendorServiceImp {
	VendorDao vendorDao;
	
	@Autowired
	public VendorService(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}

	@Override
	public boolean insertVendor(Vendor vendor) {
		if(vendorDao.insertVendor(vendor))
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean selectVendor(Vendor vendor) {
		if(vendorDao.selectvendor(vendor))
		{
			return true;
		}
		return false;
	}	
}
