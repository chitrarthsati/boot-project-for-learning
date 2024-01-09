package com.boot.features.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.boot.features.db2Repo", entityManagerFactoryRef = "secondaryEntityManagerFactory",transactionManagerRef = "secondaryTransactionManager")
@EnableTransactionManagement
public class JpaConfigSecondary {

	@Bean(name = "secondaryEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("secondaryDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource)
				.packages("com.boot.features.entitiesS")  // Replace with your secondary entity package
				.properties(jpaProperties())
				.persistenceUnit("secondary").build();
	}

	private Map<String, Object> jpaProperties() {
		Map<String, Object> properties = new HashMap<>();
//		properties.put("jdbc.driverClassName", "org.postgresql.Driver");
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.put("hibernate.hbm2ddl.create_namespaces", "true");
		return properties;
	}
}
