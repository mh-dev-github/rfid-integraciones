package com.mh.rfid.integraciones.remisiones.conversion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mh.rfid.core.conversion.service.impl.MessageConversionServiceImpl;
import com.mh.rfid.domain.esb.Remision;
import com.mh.rfid.dto.MessageDto;
import com.mh.rfid.dto.RemisionMessageDto;
import com.mh.rfid.enums.IntegracionType;
import com.mh.rfid.integraciones.remisiones.conversion.service.api.RemisionesMessageConversionService;
import com.mh.rfid.repository.esb.RemisionRepository;

import lombok.val;

@Service
public class RemisionesMessageConversionServiceImpl extends MessageConversionServiceImpl<Remision>
		implements RemisionesMessageConversionService {

	@Autowired
	private RemisionRepository repository;

	@Override
	public IntegracionType getIntegracionType() {
		return IntegracionType.REMISIONES;
	}

	@Override
	public RemisionRepository getRepository() {
		return repository;
	}

	@Override
	protected ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

	@Override
	protected MessageDto convertEntityToDto(Remision entity) {
		// @formatter:off
		val result = RemisionMessageDto.builder()
				.externalId(entity.getExternalId())
				.id(entity.getId())
				.orderNumber(entity.getOrderNumber())
				.destinationId(entity.getDestinationId())
				.remissionDate(entity.getRemissionDate())
				.build();
		// @formatter:on

		return result;
	}
}
