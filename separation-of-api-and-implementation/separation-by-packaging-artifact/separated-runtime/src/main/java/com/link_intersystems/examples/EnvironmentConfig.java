package com.link_intersystems.examples;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.dialect.HSQLDialect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class EnvironmentConfig {

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
				.addScripts("classpath:schema.sql", "classpath:persons.sql")
				.build();
	}

	@Bean
	@Qualifier("hibernateProperties")
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", HSQLDialect.class.getName());
		properties.put("hibernate.show_sql", "true");
		return properties;
	}
}
