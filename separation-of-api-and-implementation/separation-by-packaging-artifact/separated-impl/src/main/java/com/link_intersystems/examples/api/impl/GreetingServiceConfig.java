package com.link_intersystems.examples.api.impl;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class GreetingServiceConfig {

	@Bean
	public HibernateTemplate createHibernateTemplate(
			SessionFactory sessionFactory) {
		return new HibernateTemplate(sessionFactory);
	}

	@Bean
	public HibernateTransactionManager transactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager htm = new HibernateTransactionManager();
		htm.setSessionFactory(sessionFactory);
		return htm;
	}

	@Bean
	public LocalSessionFactoryBean getSessionFactory(DataSource dataSource,
			@Qualifier("hibernateProperties") Properties hibernateProperties) {
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(dataSource);
		lsfb.setPackagesToScan("com.link_intersystems.examples.domain");
		return lsfb;
	}
}
