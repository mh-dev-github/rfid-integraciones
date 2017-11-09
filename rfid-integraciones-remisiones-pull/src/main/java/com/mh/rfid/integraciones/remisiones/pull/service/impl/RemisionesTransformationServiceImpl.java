package com.mh.rfid.integraciones.remisiones.pull.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.rfid.core.pull.components.dao.RowDao;
import com.mh.rfid.core.pull.service.impl.TransformationServiceImpl;
import com.mh.rfid.domain.stage.StageError;
import com.mh.rfid.enums.IntegracionType;
import com.mh.rfid.integraciones.remisiones.pull.dto.RemisionRowDto;
import com.mh.rfid.integraciones.remisiones.pull.service.api.LookUpService;
import com.mh.rfid.integraciones.remisiones.pull.service.api.RemisionesTransformationService;

import lombok.val;

@Service
public class RemisionesTransformationServiceImpl extends TransformationServiceImpl<RemisionRowDto>
		implements RemisionesTransformationService {

	private static final String OTRAS_SALIDAS = "OTRAS_SALIDAS";

	@Autowired
	private LookUpService lookUpService;

	@Autowired
	private RowDao<RemisionRowDto> rowDao;

	@Override
	public IntegracionType getIntegracionType() {
		return IntegracionType.REMISIONES;
	}
	
	@Override
	protected RowDao<RemisionRowDto> getRowDao() {
		return rowDao;
	}

	@Override
	protected boolean translateRow(RemisionRowDto row, List<StageError> errores) {
		boolean result = super.translateRow(row, errores);

		row.setDestinationId(lookUpService.translateDestinationCode(row.getDestinationCode()));

		if (row.getDestinationId() == null) {
			row.setDestinationId(lookUpService.translateDestinationCode(OTRAS_SALIDAS));
		}

		if (row.getDestinationId() == null) {
			val codigo = LookUpServiceImpl.CODIGO_ERROR_DESTINATION_CODE;
			val mensaje = String.format(LookUpServiceImpl.MENSAJE_ERROR_DESTINATION_CODE, row.getDestinationCode());
			val error = StageError.error(getIntegracionType(), row, codigo, mensaje);
			errores.add(error);
			result = false;
		}

		return result;
	}
}
