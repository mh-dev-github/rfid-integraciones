package com.mh.rfid.integraciones.ventas.push.components.rest;

import org.springframework.stereotype.Component;

import com.mh.rfid.core.push.components.rest.RestClientImpl;
import com.mh.rfid.domain.esb.Venta;
import com.mh.rfid.dto.VentaMessageDto;

@Component
public class VentasRestClientImpl extends RestClientImpl<VentaMessageDto, Venta>
		implements VentasRestClient {

	@Override
	protected Class<VentaMessageDto> getResponseType() {
		return VentaMessageDto.class;
	}
}