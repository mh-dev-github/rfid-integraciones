package com.mh.rfid.integraciones.ventas.push.components.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mh.rfid.core.push.components.amqp.PushProducer;
import com.mh.rfid.core.push.configuration.AmqpProperties;

@Component
public class Producer extends PushProducer {

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
