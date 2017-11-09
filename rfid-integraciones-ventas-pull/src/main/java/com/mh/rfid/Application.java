package com.mh.rfid;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class Application implements CommandLineRunner{

	public static final String SPRING_CONFIG_NAME_APPLICATION = "application-rfid-integraciones-ventas-pull";

	public static void main(String[] args) {
		// @formatter:off
		new SpringApplicationBuilder(Application.class)
		.properties("spring.config.name:"+SPRING_CONFIG_NAME_APPLICATION)
		.build()
		.run(args);
		// @formatter:on
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
