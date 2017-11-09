package com.mh.rfid.integraciones.ventas.conversion.components.amqp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mh.rfid.core.conversion.components.amqp.MessageConversionConsumer;
import com.mh.rfid.core.conversion.components.amqp.MessageConversionProducer;
import com.mh.rfid.core.conversion.service.api.MessageConversionService;
import com.mh.rfid.domain.esb.Venta;
import com.mh.rfid.integraciones.ventas.conversion.service.api.VentasMessageConversionService;
import com.mh.rfid.repository.esb.BaseEntityRepository;
import com.mh.rfid.repository.esb.VentaRepository;

@Component
public class Consumer extends MessageConversionConsumer<Venta> {

	@Autowired
	private Producer producer;

	@Autowired
	private VentaRepository repository;
	
	@Autowired
	private VentasMessageConversionService messageConversionService;

	@Override
	protected MessageConversionProducer getProducer() {
		return producer;
	}

	@Override
	protected BaseEntityRepository<Venta> getRepository() {
		return repository;
	}

	@Override
	protected MessageConversionService<Venta> getMessageConversionService() {
		return messageConversionService;
	}
}