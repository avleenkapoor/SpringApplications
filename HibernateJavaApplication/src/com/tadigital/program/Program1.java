package com.tadigital.program;

import java.util.Iterator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.tadigital.entity.Product;
import com.tadigital.entity.Vendor;
import com.tadigital.hibernate.HibernateUtility;

public class Program1 {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtility.getSession();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Vendor vendor1 = new Vendor();
		Product product1 = new Product();
		vendor1.setName("Avleeen");
		vendor1.setEmail("kapoor@gmail.com");
		vendor1.setPassword("1111");
		session.save(vendor1);
		product1.setId(101);
		product1.setDescription("hey there are u happy?");
		// Insert
		session.save(product1);
		// Retrieve
		String hql = "SELECT vobj.name,vobj.email FROM Vendor vobj";
		Query query = session.createQuery(hql);
		Iterator<Object[]> iterator = query.iterate();
		while (iterator.hasNext()) {
			Object[] row = iterator.next();
			System.out.println(row[0]);
			System.out.println(row[1]);
		}
		Vendor ven = session.get(Vendor.class, 5);
		if (ven != null) {
			// delete
			session.delete(ven);
		}
		ven = session.get(Vendor.class, 6);
		if (ven != null) {
			ven.setEmail("avleenkapoor.ak@gmail.com");
			// update
			session.update(ven);
		}
		transaction.commit();
		session.close();
		HibernateUtility.closeSessionFactory();
	}
}
