package com.xpy.utils;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	private static final Configuration CONFIG;
	private static final SessionFactory SESSIONFACTORY;
	
	static {
		CONFIG = new Configuration();
		CONFIG.configure();
		SESSIONFACTORY = CONFIG.buildSessionFactory();
	}
	
	public static Session getSession() {
		return SESSIONFACTORY.openSession();
	}
	
	public static Session getCurrentSession() {
		return SESSIONFACTORY.getCurrentSession();
	}
	
}
