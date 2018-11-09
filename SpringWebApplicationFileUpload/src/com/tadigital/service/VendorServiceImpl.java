package com.tadigital.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadigital.dao.VendorDao;

import com.tadigital.entity.Vendor;

@Service
public class VendorServiceImpl implements VendorService {
	private final static Logger LOGGER=Logger.getLogger(VendorServiceImpl.class);
	VendorDao vendorDao;

	@Autowired
	public VendorServiceImpl(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}


	@Override
	public String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
	    String[] items = contentDisp.split(";");
	    String fileName = "";
	    for (String s : items) {
	        if (s.trim().startsWith("filename")) {
	            fileName = s.substring(s.indexOf("=") + 2, s.length()-1);
	        }
	    }
	    
	    FileOutputStream fos = null;
	    InputStream is = null;
	   
	    try {
	    	File file = new File("D:\\" + fileName);
	        fos = new FileOutputStream(file);
	        is = part.getInputStream();
	
	        int read = 0;
	        final byte[] bytes = new byte[1024];
	
	        while((read = is.read(bytes)) != -1) {
	            fos.write(bytes, 0, read);
	        }
	        
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    } finally {
	    	try {
	    		if (fos != null) {
		            fos.close();
		        }
		        if (is != null) {
		        	is.close();
		        }
			} catch (IOException ioe) {
		        ioe.printStackTrace();
		    }
	    }
	    
	    return fileName;
	}

	@Override
	public boolean insertVendor(Vendor vendor) {
		vendor.setFileName(getFileName(vendor.getPart()));	
		if (vendorDao.insertVendor(vendor)) {
			return true;
		}
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

	@Override
	public void editVendor(int vid, String name, String email) {

		vendorDao.editVendor(vid, name, email);

	}

	@Override
	public void deleteVendor(int vid) {
		vendorDao.deleteVendor(vid);

	}
}
