package com.mh.rfid.integraciones.remisiones.push.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.rfid.core.push.service.impl.VerificationServiceImpl;
import com.mh.rfid.domain.esb.Remision;
import com.mh.rfid.dto.RemisionMessageDto;
import com.mh.rfid.enums.IntegracionType;
import com.mh.rfid.integraciones.remisiones.push.components.rest.RemisionesRestClient;
import com.mh.rfid.integraciones.remisiones.push.service.api.RemisionesVerificactionService;
import com.mh.rfid.repository.esb.RemisionRepository;

@Service
public class RemisionesVerificactionServiceImpl extends VerificationServiceImpl<RemisionMessageDto, Remision>
		implements RemisionesVerificactionService {

	@Autowired
	private RemisionRepository repository;

	@Autowired
	private RemisionesRestClient restClient;

	@Override
	public IntegracionType getIntegracionType() {
		return IntegracionType.REMISIONES;
	}

	@Override
	public RemisionRepository getRepository() {
		return repository;
	}

	@Override
	protected RemisionesRestClient getRestClient() {
		return restClient;
	}

	@Override
	protected void compare(Remision entity, RemisionMessageDto dto, List<String> errores) {
		compare("externalId", entity.getExternalId(), dto.getExternalId(), errores);
		compare("id", entity.getId(), dto.getId(), errores);
		compare("orderNumber", entity.getOrderNumber(), dto.getOrderNumber(), errores);
		compare("remissionDate", entity.getRemissionDate(), dto.getRemissionDate(), errores);
		compare("destinationId", entity.getDestinationId(), dto.getDestinationId(), errores);
	}
}