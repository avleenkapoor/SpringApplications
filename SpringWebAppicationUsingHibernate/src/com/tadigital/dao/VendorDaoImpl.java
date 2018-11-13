package com.tadigital.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import com.tadigital.entity.Vendor;

@Repository
public class VendorDaoImpl implements VendorDao {
	private SessionFactory sessionFactory;

	@Autowired
	public VendorDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean insertVendor(Vendor vendor) {
		boolean status = false;

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(vendor);

		transaction.commit();
		status = true;

		session.close();

		return status;
	}

	@Override
	public boolean selectvendor(Vendor vendor) {
		boolean status = false;
		Session session = sessionFactory.openSession();
		String hql = "FROM Vendor v WHERE v.email='" + vendor.getEmail() + "' AND v.password='" + vendor.getPassword()
				+ "'";
		Query query = session.createQuery(hql);
		List<Vendor> vendors = query.list();
		if (vendors.size() != 0) {
			status = true;

			Vendor v = vendors.get(0);

			vendor.setId(v.getId());
			vendor.setName(v.getName());
			vendor.setFilename(v.getFilename());
		}

		session.close();
		return status;
	}

	@Override
	public List<Vendor> selectAllVendors() {
		Session session = sessionFactory.openSession();

		String hql = "FROM Vendor";
		Query query = session.createQuery(hql);

		List<Vendor> vendors = query.list();

		List<Vendor> vendorList = new ArrayList<>(vendors);

		session.close();
		return vendorList;
	}

	@Override
	public boolean editVendor(int vid, String name, String email) {
		boolean status = false;

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Vendor v = new Vendor();
		v = session.get(Vendor.class, vid);
		v.setEmail(email);
		v.setName(name);
		session.update(v);
		transaction.commit();
		status = true;

		session.close();

		return status;
	}

	@Override
	public boolean deleteVendor(int vid) {
		boolean status = false;

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Vendor vendor = session.get(Vendor.class, vid);
		if (vendor != null) {
			session.delete(vendor);

			transaction.commit();
			status = true;
		}
		session.close();

		return status;
	}

	@Override
	public Vendor selectvendorById(int vid) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Vendor v = new Vendor();
		v = session.get(Vendor.class, vid);

		transaction.commit();
		return v;
	}

}
