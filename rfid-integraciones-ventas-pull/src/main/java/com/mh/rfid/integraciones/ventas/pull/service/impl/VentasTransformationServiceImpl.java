package com.mh.rfid.integraciones.ventas.pull.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.rfid.core.pull.components.dao.RowDao;
import com.mh.rfid.core.pull.service.impl.TransformationServiceImpl;
import com.mh.rfid.domain.stage.StageError;
import com.mh.rfid.enums.IntegracionType;
import com.mh.rfid.integraciones.ventas.pull.dto.VentaRowDto;
import com.mh.rfid.integraciones.ventas.pull.service.api.LookUpService;
import com.mh.rfid.integraciones.ventas.pull.service.api.VentasTransformationService;

import lombok.val;

@Service
public class VentasTransformationServiceImpl extends TransformationServiceImpl<VentaRowDto>
		implements VentasTransformationService {
	
	@Autowired
	private LookUpService lookUpService;
	
	@Autowired
	private RowDao<VentaRowDto> rowDao;
	
	@Override
	public IntegracionType getIntegracionType() {
		return IntegracionType.VENTAS;
	}
	
	@Override
	protected RowDao<VentaRowDto> getRowDao() {
		return rowDao;
	}

	@Override
	protected boolean translateRow(VentaRowDto row, List<StageError> errores) {
		boolean result = super.translateRow(row, errores);

		row.setProductId(lookUpService.translateProductCode(row.getBarCode()));

		if (row.getProductId() == null) {
			val codigo = LookUpServiceImpl.CODIGO_ERROR_PRODUCT_CODE;
			val mensaje = String.format(LookUpServiceImpl.MENSAJE_ERROR_PRODUCT_CODE, row.getBarCode());
			val error = StageError.error(getIntegracionType(), row, codigo, mensaje);
			errores.add(error);
			result = false;
		}

		return result;
	}
}
