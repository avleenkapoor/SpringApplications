package com.tadigital.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {
	private static SessionFactory sessionFactory;
	static {
		Configuration configuration = new Configuration();
		configuration.configure();
		sessionFactory = configuration.buildSessionFactory();
	}

	public static SessionFactory getSession() {
		return sessionFactory;
	}

	public static void closeSessionFactory() {
		sessionFactory.close();
	}
}
