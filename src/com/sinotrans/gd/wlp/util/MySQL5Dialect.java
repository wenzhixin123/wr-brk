package com.sinotrans.gd.wlp.util;

import java.sql.Types;

import org.hibernate.Hibernate;


public class MySQL5Dialect extends org.hibernate.dialect.MySQL5Dialect {
	
	public MySQL5Dialect() {
		super();
		registerHibernateType(Types.CHAR, Hibernate.STRING.getName());
	}

}
