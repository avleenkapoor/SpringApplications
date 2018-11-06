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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.nativejdbc.JBossNativeJdbcExtractor;
import org.springframework.stereotype.Repository;

import com.tadigital.entity.Vendor;

@Repository
public class VendorDaoImpl implements VendorDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource datasource) {
		jdbcTemplate = new JdbcTemplate(datasource);
	}

	public boolean insertVendor(Vendor vendor) {
		boolean status = false;
		String sql = "INSERT INTO vendor_info(Name,Email,Password) VALUES(?,?,?)";
		int row = jdbcTemplate.update(sql, vendor.getName(), vendor.getEmail(), vendor.getPassword());
		if (row != 0) {
			status = true;
		}
		return status;
	}

	@Override
	public boolean selectvendor(Vendor vendor) {
		boolean status = false;
		String sql = "SELECT * FROM vendor_info WHERE Email=? AND Password=?";
		Vendor v = new Vendor();
		try {
			v = jdbcTemplate.queryForObject(sql, new Object[] { vendor.getEmail(), vendor.getPassword() },
					new RowMapper<Vendor>() {

						@Override
						public Vendor mapRow(ResultSet rs, int rowCount) throws SQLException {
							Vendor ven = new Vendor();
							ven.setId(rs.getInt(1));
							ven.setName(rs.getString(2));
							ven.setName(rs.getString(3));
							ven.setPassword(rs.getString(4));
							return ven;
						}
					});
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
		if (v != null) {
			status = true;
		}

		return status;
	}

	@Override
	public List<Vendor> selectAllVendors() {
		List<Vendor> list = new ArrayList<>();
		String sql = "SELECT * FROM vendor_info";
		list = jdbcTemplate.query(sql, new RowMapper<Vendor>() {

			@Override
			public Vendor mapRow(ResultSet rs, int rowcount) throws SQLException {
				Vendor vendor = new Vendor();
				vendor.setId(rs.getInt(1));
				vendor.setName(rs.getString(2));
				vendor.setEmail(rs.getString(3));
				vendor.setPassword(rs.getString(4));
				return vendor;
			}

		});
		return list;
	}

	@Override
	public boolean editVendor(int vid, String name, String email) {
		boolean status = false;
		int rs = 0;
		String sql = "UPDATE vendor_info SET Name=?, Email=? WHERE id=?";
		rs = jdbcTemplate.update(sql, name, email, vid);
		System.out.println("pahunch gya " + vid + name + email + "Rows affected" + rs);
		if (rs != 0) {
			status = true;
		}
		return status;
	}

	@Override
	public boolean deleteVendor(int vid) {

		boolean status = false;
		int rs = 0;
		String sql = "DELETE FROM vendor_info WHERE Id=?";
		rs = jdbcTemplate.update(sql, vid);
		if (rs != 0) {
			status = true;
		}
		return status;
	}

	@Override
	public Vendor selectvendorById(int vid) {
		boolean status = false;
		String sql = "SELECT * FROM vendor_info WHERE ID=?";
		Vendor vendor = new Vendor();
		vendor.setId(vid);
		Vendor v = new Vendor();
		v = jdbcTemplate.queryForObject(sql, new Object[] { vid }, new RowMapper<Vendor>() {

			@Override
			public Vendor mapRow(ResultSet rs, int arg1) throws SQLException {
				Vendor ven = new Vendor();
				ven.setId(rs.getInt(1));
				ven.setName(rs.getString(2));
				ven.setEmail(rs.getString(3));
				ven.setPassword(rs.getString(4));
				return ven;
			}

		});
		if (v != null) {
			return v;
		}
		return null;
	}

}
