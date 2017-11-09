package com.mh.rfid.integraciones.remisiones.pull.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mh.rfid.Application;

import lombok.val;

@Configuration
@PropertySource("classpath:" + Application.SPRING_CONFIG_NAME_APPLICATION + ".properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = DatabaseConfiguration.BASE_PACKAGES)
public class DatabaseConfiguration {

	static final String CONFIGURATION_PROPERTIES_PREFIX = "spring.datasource";

	static final String BASE_PACKAGES = "com.mh.rfid.repository";

	static final String[] PACKAGES_TO_SCAN = { "com.mh.rfid.domain", "com.mh.rfid.core.data.jpa.converters" };

	public static final String JDBC_TEMPLATE = "stageJdbcTemplate";

	@Primary
	@Bean
	@ConfigurationProperties(prefix = CONFIGURATION_PROPERTIES_PREFIX)
	public DataSource dataSource() {
		val result = DataSourceBuilder.create().build();
		return result;
	}

	@Primary
	@Bean(name = JDBC_TEMPLATE)
	@Autowired
	public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
		val result = new NamedParameterJdbcTemplate(dataSource);
		return result;
	}

	@Primary
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		val result = new JpaTransactionManager(entityManagerFactory);
		return result;
	}

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			DataSource dataSource) {
		val result = builder.dataSource(dataSource).packages(PACKAGES_TO_SCAN).build();
		return result;
	}
}
