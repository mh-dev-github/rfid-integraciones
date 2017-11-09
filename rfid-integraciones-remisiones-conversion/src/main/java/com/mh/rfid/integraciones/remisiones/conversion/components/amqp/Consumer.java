package com.mh.rfid.integraciones.remisiones.conversion.components.amqp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mh.rfid.core.conversion.components.amqp.MessageConversionConsumer;
import com.mh.rfid.core.conversion.components.amqp.MessageConversionProducer;
import com.mh.rfid.domain.esb.Remision;
import com.mh.rfid.integraciones.remisiones.conversion.service.api.RemisionesMessageConversionService;
import com.mh.rfid.repository.esb.BaseEntityRepository;
import com.mh.rfid.repository.esb.RemisionRepository;

@Component
public class Consumer extends MessageConversionConsumer<Remision> {

	@Autowired
	private Producer producer;

	@Autowired
	private RemisionRepository repository;

	@Autowired
	private RemisionesMessageConversionService messageConversionService;

	@Override
	protected MessageConversionProducer getProducer() {
		return producer;
	}

	@Override
	protected BaseEntityRepository<Remision> getRepository() {
		return repository;
	}

	@Override
	protected RemisionesMessageConversionService getMessageConversionService() {
		return messageConversionService;
	}
}