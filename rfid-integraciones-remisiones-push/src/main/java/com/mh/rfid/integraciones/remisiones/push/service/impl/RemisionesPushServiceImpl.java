package com.mh.rfid.integraciones.remisiones.push.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.rfid.core.push.service.impl.PushServiceImpl;
import com.mh.rfid.domain.esb.Remision;
import com.mh.rfid.dto.RemisionMessageDto;
import com.mh.rfid.enums.IntegracionType;
import com.mh.rfid.integraciones.remisiones.push.components.rest.RemisionesRestClient;
import com.mh.rfid.integraciones.remisiones.push.service.api.RemisionesPushService;
import com.mh.rfid.repository.esb.RemisionRepository;

@Service
public class RemisionesPushServiceImpl extends PushServiceImpl<RemisionMessageDto, Remision>
		implements RemisionesPushService {

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
}
