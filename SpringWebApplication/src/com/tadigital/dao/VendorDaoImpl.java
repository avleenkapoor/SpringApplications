package com.tadigital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Generated;
import javax.sql.DataSource;

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
			pStmt.setString(1, vendor.getEmail());
			pStmt.setString(1, vendor.getPassword());
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
	public boolean selectAllVendors() {
		// TODO Auto-generated method stub
		return false;
	}

}
