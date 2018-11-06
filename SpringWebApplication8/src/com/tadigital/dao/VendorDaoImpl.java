package com.tadigital.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.tadigital.entity.Vendor;

@Repository
public class VendorDaoImpl implements VendorDao {
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource datasource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(datasource);
	}

	public boolean insertVendor(Vendor vendor) {
		boolean status = false;
		String sql = "INSERT INTO vendor_info(Name,Email,Password) VALUES(:name,:email,:password)";
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(vendor);
		int row = jdbcTemplate.update(sql, parameters);
		if (row != 0) {
			status = true;
		}
		return status;
	}

	@Override
	public boolean selectvendor(Vendor vendor) {
		boolean status = false;
		String sql = "SELECT * FROM vendor_info WHERE Email=:email AND Password=:password";
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(vendor);
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
		String sql = "UPDATE vendor_info SET Name=:name, Email=:email WHERE id=:id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", name);
		parameters.addValue("email", email);
		parameters.addValue("id", vid);
		rs = jdbcTemplate.update(sql, parameters);
		if (rs != 0) {
			status = true;
		}
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
				return ven;
			}

		});
		if (v != null) {
			return v;
		}
		return null;
	}

}
