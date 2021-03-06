package com.tadigital.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.omg.CORBA.Request;
import org.springframework.stereotype.Repository;

import com.tadigital.entity.Vendor;

@Repository
public class VendorDaoImpl extends Dao implements VendorDao {
	public boolean insertVendor(Vendor vendor) {
		boolean status = false;

		String sql = "INSERT INTO vendor_info(Name,Email,Password) VALUES(?,?,?)";
		DataSource dataSource = getDataSource();
		try (Connection con = dataSource.getConnection(); PreparedStatement pStmt = con.prepareStatement(sql)) {
			pStmt.setString(1, vendor.getName());
			pStmt.setString(2, vendor.getEmail());
			pStmt.setString(3, vendor.getPassword());
			int rows = pStmt.executeUpdate();
			if (rows != 0) {
				status = true;
			}
		} catch (SQLException sqle) {

		}
		return status;
	}

	@Override
	public boolean selectvendor(Vendor vendor) {
		boolean status = false;
		ResultSet rs = null;
		String sql = "SELECT * FROM vendor_info WHERE Email=? AND Password=?";
		DataSource dataSource = getDataSource();

		try (Connection con = dataSource.getConnection(); PreparedStatement pStmt = con.prepareStatement(sql)) {
			pStmt.setString(1, vendor.getEmail());
			pStmt.setString(2, vendor.getPassword());
			rs = pStmt.executeQuery();

			if (rs.next()) {
				status = true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (rs != null)
				closeResultSet(rs);
		}
		return status;
	}

	@Override
	public ArrayList<Vendor >selectAllVendors() {
		boolean status = false;
		ResultSet rs = null;
		List<Vendor>list=new ArrayList<>();
		String sql = "SELECT * FROM vendor_info";
		DataSource dataSource = getDataSource();

		try (Connection con = dataSource.getConnection(); PreparedStatement pStmt = con.prepareStatement(sql)) {	
			rs = pStmt.executeQuery();

			while (rs.next()) {
				Vendor vendor=new Vendor();
				vendor.setName(rs.getString(2));
				vendor.setEmail(rs.getString(3));
				vendor.setId(rs.getInt(1));
				list.add(vendor);
				status = true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (rs != null)
				closeResultSet(rs);
		}
		return (ArrayList<Vendor>) list;
	}

}
