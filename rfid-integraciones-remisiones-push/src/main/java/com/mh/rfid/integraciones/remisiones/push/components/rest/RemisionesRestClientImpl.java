package com.mh.rfid.integraciones.remisiones.push.components.rest;

import org.springframework.stereotype.Component;

import com.mh.rfid.core.push.components.rest.RestClientImpl;
import com.mh.rfid.core.push.dto.GetResponseType;
import com.mh.rfid.domain.esb.Remision;
import com.mh.rfid.dto.RemisionMessageDto;

import lombok.val;

@Component
public class RemisionesRestClientImpl extends RestClientImpl<RemisionMessageDto, Remision>
		implements RemisionesRestClient {

	@Override
	protected Class<RemisionMessageDto> getResponseType() {
		return RemisionMessageDto.class;
	}

	@Override
	public RemisionMessageDto get(Remision entity) {
		val responseType = GetRemisionResponseType.class;
		val response = get(entity, responseType);
		val body = response.getBody();
		val items = body.getItems();

		RemisionMessageDto result = null;

		if (items != null) {
			if (items.size() >= 1) {
				result = items.get(0);
			}
		}

		return result;
	}

	@Override
	protected String getApiUriResourceGetById(Remision entity) {
		return getApiUriResource() + "?remission_number=" + entity.getExternalId();
	}
}

class GetRemisionResponseType extends GetResponseType<RemisionMessageDto> {

	private static final long serialVersionUID = 1L;

}