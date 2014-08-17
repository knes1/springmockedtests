package com.knesek.springmockedtests;

import org.springframework.context.annotation.Bean;
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
 * Common configuration for integration tests. Note that, in this example, it differs from the
 * actual AppConfig only in that @ComponentScan is omitted - we don't want to load entire application
 * context during test, only the beans specified in the integration test itself.
 *
 * An actual application would have testing database defined in here and other testing environment specific
 * configurations.
 *
 * @author knesek
 * Created on: 17/08/14
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.knesek.springmockedtests.repository")
public class TestAppConfig {

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
