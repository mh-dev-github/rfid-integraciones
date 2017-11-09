package com.mh.rfid.integraciones.ventas.pull.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.mh.rfid.Application;
import com.mh.rfid.core.pull.configuration.PullAmqpConfiguration;

@Configuration
@PropertySource("classpath:" + Application.SPRING_CONFIG_NAME_APPLICATION + ".properties")
public class AmqpConfiguration extends PullAmqpConfiguration {

}
