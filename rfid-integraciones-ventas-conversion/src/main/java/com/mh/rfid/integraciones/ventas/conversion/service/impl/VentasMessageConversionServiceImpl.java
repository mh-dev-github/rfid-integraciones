package com.mh.rfid.integraciones.ventas.conversion.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mh.rfid.core.conversion.service.impl.MessageConversionServiceImpl;
import com.mh.rfid.domain.esb.Venta;
import com.mh.rfid.dto.MessageDto;
import com.mh.rfid.dto.VentaMessageDto;
import com.mh.rfid.enums.IntegracionType;
import com.mh.rfid.integraciones.ventas.conversion.service.api.VentasMessageConversionService;
import com.mh.rfid.repository.esb.VentaRepository;

import lombok.val;

@Service
public class VentasMessageConversionServiceImpl extends MessageConversionServiceImpl<Venta>
		implements VentasMessageConversionService {

	@Autowired
	private VentaRepository repository;

	@Override
	public IntegracionType getIntegracionType() {
		return IntegracionType.VENTAS;
	}

	@Override
	public VentaRepository getRepository() {
		return repository;
	}

	@Override
	protected ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

	@Override
	protected MessageDto convertEntityToDto(Venta entity) {
		// @formatter:off
				val result = VentaMessageDto.builder()
						.externalId(entity.getExternalId())
						.id(entity.getId())
						.storeCode(entity.getStoreCode())
						.saleDate(entity.getSaleDate())
						.lineas(new ArrayList<>())
						.build();
				// @formatter:on

		entity.getLineas().forEach(a -> {
			val linea = new VentaMessageDto.Linea(a.getBarCode(), a.getQuantity());
			result.getLineas().add(linea);
		});

		return result;
	}
}
