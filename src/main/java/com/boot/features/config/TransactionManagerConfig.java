package com.boot.features.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
public class TransactionManagerConfig {

	@Autowired
	@Qualifier("primaryEntityManagerFactory") 
	private EntityManagerFactory primaryEntityManagerFactory;
	
	@Autowired
	@Qualifier("secondaryEntityManagerFactory") 
	private EntityManagerFactory secondaryEntityManagerFactory;
	
	
	@Primary
	@Bean(name = "primaryTransactionManager")
	public PlatformTransactionManager primaryTransactionManager() {
		return new JpaTransactionManager(primaryEntityManagerFactory);
	}

	@Bean(name = "secondaryTransactionManager")
	public PlatformTransactionManager secondaryTransactionManager() {
		return new JpaTransactionManager(secondaryEntityManagerFactory);
	}
}
