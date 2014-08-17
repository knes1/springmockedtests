package com.knesek.springmockedtests;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Demo application's main configuration that configures JPA, Hibernate, Spring Data, and H2 embedded database.
 * @ComponentScan and auto component detection is used for the rest.
 *
 * @author knesek
 * Created on: 17/08/14
 */
@ComponentScan
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.knesek.springmockedtests.repository")
public class AppConfig {

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter
			jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(dataSource);
		lef.setJpaVendorAdapter(jpaVendorAdapter);
		lef.setPackagesToScan("com.knesek.springmockedtests.model");
		return lef;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.H2);
		return hibernateJpaVendorAdapter;
	}
}
