package com.mh.rfid.integraciones.ventas.conversion.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.mh.rfid.Application;
import com.mh.rfid.core.conversion.configuration.MessageConversionAmqpConfiguration;

@Configuration
@PropertySource("classpath:" + Application.SPRING_CONFIG_NAME_APPLICATION + ".properties")
public class AmqpConfiguration extends MessageConversionAmqpConfiguration {
	
}
