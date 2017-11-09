package com.mh.rfid.integraciones.remisiones.push.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.mh.rfid.Application;
import com.mh.rfid.core.push.configuration.ApesRestTemplateConfiguration;

@Configuration
@PropertySource("classpath:" + Application.SPRING_CONFIG_NAME_APPLICATION + ".properties")
public class RestTemplateConfiguration extends ApesRestTemplateConfiguration {

}