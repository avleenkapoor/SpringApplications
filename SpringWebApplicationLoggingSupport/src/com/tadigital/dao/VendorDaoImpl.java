package com.tadigital.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.tadigital.entity.Vendor;

@Repository
public class VendorDaoImpl implements VendorDao {
	private static final Logger LOGGER = Logger.getLogger("VendorDaoImpl.class");
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource datasource) {
		LOGGER.info("setDataSource() ex");
		jdbcTemplate = new NamedParameterJdbcTemplate(datasource);
	}

	public boolean insertVendor(Vendor vendor) {
		
		LOGGER.info("insertVendoRr() dao ex");
		boolean status = false;
		String sql = "INSERT INTO vendor_info(Name,Email,Password) VALUES(:name,:email,:password)";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", vendor.getName());
		parameters.addValue("email", vendor.getEmail());
		parameters.addValue("password", vendor.getPassword());
		int row = jdbcTemplate.update(sql, parameters);
		if (row != 0) {
			status = true;
		}
		LOGGER.info("insertVendor() dao ter");
		return status;
	}

	@Override
	public boolean selectvendor(Vendor vendor) {
		LOGGER.info("selectVendor() dao ex");
		boolean status = false;
		String sql = "SELECT * FROM vendor_info WHERE Email=:email AND Password=:password";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("email", vendor.getEmail());
		parameters.addValue("password", vendor.getPassword());
		Vendor v = new Vendor();
		try {
			v = jdbcTemplate.queryForObject(sql, parameters, new RowMapper<Vendor>() {

				@Override
				public Vendor mapRow(ResultSet rs, int rowCount) throws SQLException {
					Vendor ven = new Vendor();
					ven.setId(rs.getInt(1));
					ven.setName(rs.getString(2));
					ven.setName(rs.getString(3));
					ven.setPassword(rs.getString(4));
					LOGGER.info("selectVendor() dao Terminated");
					return ven;
				}
			});
		} catch (EmptyResultDataAccessException e) {
			LOGGER.info("selectVendor()  dao Terminated");
			return false;
		}
		if (v != null) {
			status = true;
		}
		LOGGER.info("selectVendor() dao Terminated");
		return status;
	}

	@Override
	public List<Vendor> selectAllVendors() {
		LOGGER.info("List<Vendor> Executed");
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
				LOGGER.info("List<Vendor> Terminated");
				return vendor;
			}

		});
		LOGGER.info("List<Vendor> Terminated");
		return list;
	}

	@Override
	public boolean editVendor(int vid, String name, String email) {
		LOGGER.info("editVendor() in Dao Executed");
		boolean status = false;
		int rs = 0;
		String sql = "UPDATE vendor_info SET Name=:name, Email=:email WHERE id=:id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", name);
		parameters.addValue("email", email);
		parameters.addValue("id", vid);
		rs = jdbcTemplate.update(sql, parameters);
		if (rs != 0) {
			status = true;
		}
		LOGGER.info("editVendor() in Dao ETerminated");
		return status;
	}

	@Override
	public boolean deleteVendor(int vid) {

		boolean status = false;
		int rs = 0;
		String sql = "DELETE FROM vendor_info WHERE Id=:id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", vid);
		rs = jdbcTemplate.update(sql, parameters);
		if (rs != 0) {
			status = true;
		}
		return status;
	}

	@Override
	public Vendor selectvendorById(int vid) {
		LOGGER.info("selectvendor() in Dao Executed");
		boolean status = false;
		String sql = "SELECT * FROM vendor_info WHERE ID=:id";
		Vendor vendor = new Vendor();
		vendor.setId(vid);
		Vendor v = new Vendor();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", vid);
		v = jdbcTemplate.queryForObject(sql, parameters, new RowMapper<Vendor>() {

			@Override
			public Vendor mapRow(ResultSet rs, int arg1) throws SQLException {
				Vendor ven = new Vendor();
				ven.setId(rs.getInt(1));
				ven.setName(rs.getString(2));
				ven.setEmail(rs.getString(3));
				ven.setPassword(rs.getString(4));
				LOGGER.info("editVendor() in Dao Terminated");
				return ven;
			}

		});
		if (v != null) {
			return v;
		}
		LOGGER.info("selectVendor() in Dao Terminated");
		return null;
	}

}
