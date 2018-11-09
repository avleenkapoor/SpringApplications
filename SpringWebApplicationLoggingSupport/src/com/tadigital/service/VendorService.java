package com.tadigital.service;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadigital.dao.VendorDao;
import com.tadigital.entity.Vendor;

@Service
public class VendorService implements VendorServiceImp {
	private static final Logger LOGGER=Logger.getLogger("VendorService.class");
	VendorDao vendorDao;

	@Autowired
	public VendorService(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}

	@Override
	public boolean insertVendor(Vendor vendor) {
		LOGGER.info("insertVendor() in sEvice");
		if (vendorDao.insertVendor(vendor)) {
			LOGGER.info("insertVendor() in Terminated");
			return true;
		}
		LOGGER.info("insertVendor() in Terminated");
		return false;
	}

	@Override
	public boolean selectVendor(Vendor vendor) {
		if (vendorDao.selectvendor(vendor)) {
			return true;
		}
		return false;
	}

	@Override
	public List<Vendor> selectAllUsers() {
		List<Vendor> list;
		list = (List<Vendor>) vendorDao.selectAllVendors();
		return list;
	}

	@Override
	public Vendor selectVendorById(int vid) {
		Vendor vendor = new Vendor();
		vendor = vendorDao.selectvendorById(vid);
		return vendor;
	}

	public void editVendor(int vid, String name, String email) {

		vendorDao.editVendor(vid, name, email);

	}

	@Override
	public void deleteVendor(int vid) {
		vendorDao.deleteVendor(vid);

	}
}
