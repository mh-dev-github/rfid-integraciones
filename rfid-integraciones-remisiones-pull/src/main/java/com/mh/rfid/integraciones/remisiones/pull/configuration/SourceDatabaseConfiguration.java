package com.mh.rfid.integraciones.remisiones.pull.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.val;

@Configuration
@EnableTransactionManagement
public class SourceDatabaseConfiguration {

	public static final String CONFIGURATION_PROPERTIES_PREFIX = "source.datasource";
	
	public static final String DATA_SOURCE = "sourceDataSource";
	
	public static final String JDBC_TEMPLATE = "sourceJdbcTemplate";

	@Bean(name = DATA_SOURCE)
	@ConfigurationProperties(prefix = CONFIGURATION_PROPERTIES_PREFIX)
	public DataSource dataSource() {
		val result = DataSourceBuilder.create().build();
		return result;
	}

	@Bean(name = JDBC_TEMPLATE)
	@Autowired
	public NamedParameterJdbcTemplate jdbcTemplate(@Qualifier(DATA_SOURCE) DataSource dataSource) {
		val result = new NamedParameterJdbcTemplate(dataSource);
		return result;
	}
}