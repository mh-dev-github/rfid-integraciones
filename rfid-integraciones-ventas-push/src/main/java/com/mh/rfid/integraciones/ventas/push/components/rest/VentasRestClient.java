package com.mh.rfid.integraciones.ventas.push.components.rest;

import com.mh.rfid.core.push.components.rest.RestClient;
import com.mh.rfid.domain.esb.Venta;
import com.mh.rfid.dto.VentaMessageDto;

public interface VentasRestClient extends RestClient<VentaMessageDto, Venta> {

}
