package com.mh.rfid.integraciones.remisiones.pull.service.api;

import com.mh.rfid.core.pull.service.api.LoadService;
import com.mh.rfid.domain.esb.Remision;
import com.mh.rfid.integraciones.remisiones.pull.dto.RemisionRowDto;

public interface RemisionesLoadService extends LoadService<RemisionRowDto, Remision> {

}
