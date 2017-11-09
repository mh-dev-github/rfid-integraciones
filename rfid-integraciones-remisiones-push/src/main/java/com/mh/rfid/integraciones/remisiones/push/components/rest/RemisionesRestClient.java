package com.mh.rfid.integraciones.remisiones.push.components.rest;

import com.mh.rfid.core.push.components.rest.RestClient;
import com.mh.rfid.domain.esb.Remision;
import com.mh.rfid.dto.RemisionMessageDto;

public interface RemisionesRestClient extends RestClient<RemisionMessageDto, Remision> {

}
