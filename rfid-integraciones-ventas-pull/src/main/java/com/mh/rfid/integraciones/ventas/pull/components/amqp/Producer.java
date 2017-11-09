package com.mh.rfid.integraciones.ventas.pull.components.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mh.rfid.core.pull.components.amqp.PullProducer;
import com.mh.rfid.core.pull.configuration.AmqpProperties;
import com.mh.rfid.integraciones.ventas.pull.dto.VentaRowDto;

@Component
public class Producer extends PullProducer<VentaRowDto> {

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
