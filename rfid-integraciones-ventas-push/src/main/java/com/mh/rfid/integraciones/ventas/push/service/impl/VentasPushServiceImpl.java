package com.mh.rfid.integraciones.ventas.push.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.rfid.core.push.service.impl.PushServiceImpl;
import com.mh.rfid.domain.esb.Venta;
import com.mh.rfid.dto.VentaMessageDto;
import com.mh.rfid.enums.IntegracionType;
import com.mh.rfid.integraciones.ventas.push.components.rest.VentasRestClient;
import com.mh.rfid.integraciones.ventas.push.service.api.VentasPushService;
import com.mh.rfid.repository.esb.VentaRepository;

@Service
public class VentasPushServiceImpl extends PushServiceImpl<VentaMessageDto, Venta>
		implements VentasPushService {

	@Autowired
	private VentaRepository repository;

	@Autowired
	private VentasRestClient restClient;

	@Override
	public IntegracionType getIntegracionType() {
		return IntegracionType.VENTAS;
	}

	@Override
	public VentaRepository getRepository() {
		return repository;
	}
	
	@Override
	protected VentasRestClient getRestClient() {
		return restClient;
	}

	@Override
	protected boolean requiereVerificacion() {
		return false;
	}
}