package com.mh.rfid.integraciones.ventas.pull.service.api;

import com.mh.rfid.core.pull.service.api.LoadService;
import com.mh.rfid.domain.esb.Venta;
import com.mh.rfid.integraciones.ventas.pull.dto.VentaRowDto;

public interface VentasLoadService extends LoadService<VentaRowDto, Venta> {

}
