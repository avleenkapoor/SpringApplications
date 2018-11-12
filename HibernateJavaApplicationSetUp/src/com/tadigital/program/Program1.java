package com.tadigital.program;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tadigital.entity.Product;
import com.tadigital.entity.Vendor;
import com.tadigital.hibernate.HibernateUtility;

public class Program1 {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtility.getSession();
		HibernateUtility.closeSessionFactory();
	}
}
