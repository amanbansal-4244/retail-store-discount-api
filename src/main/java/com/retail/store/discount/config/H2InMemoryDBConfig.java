package com.retail.store.discount.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.annotation.JsonProperty;

@EnableAutoConfiguration
@Configuration
@EnableTransactionManagement
@ComponentScan("com.retail.store.discount.*")
@EnableJpaRepositories(basePackages = { "com.retail.store.discount.repository" })
@EntityScan("com.retail.store.discount.*")
class H2InMemoryDBConfig {

	@JsonProperty("inMemory")
	private boolean inMemory;

	@JsonProperty
	private DataSourceFactory database;

	public boolean isInMemory() {
		return inMemory;
	}

	public DataSourceFactory getDatabase() {
		return database;
	}
}
