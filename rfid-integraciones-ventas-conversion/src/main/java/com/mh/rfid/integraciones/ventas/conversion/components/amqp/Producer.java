package com.mh.rfid.integraciones.ventas.conversion.components.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mh.rfid.core.conversion.components.amqp.MessageConversionProducer;
import com.mh.rfid.core.conversion.configuration.AmqpProperties;

@Component
public class Producer extends MessageConversionProducer {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Autowired
	private AmqpProperties properties;
	
	@Override
	protected AmqpTemplate getAmqpTemplate() {
		return amqpTemplate;
	}

	@Override
	protected AmqpProperties getProperties() {
		return properties;
	}
}