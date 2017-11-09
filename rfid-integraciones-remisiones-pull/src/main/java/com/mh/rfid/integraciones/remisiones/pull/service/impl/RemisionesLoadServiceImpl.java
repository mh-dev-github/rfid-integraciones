package com.mh.rfid.integraciones.remisiones.pull.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.rfid.core.pull.components.dao.RowDao;
import com.mh.rfid.core.pull.service.impl.LoadServiceImpl;
import com.mh.rfid.domain.esb.Remision;
import com.mh.rfid.enums.IntegracionType;
import com.mh.rfid.integraciones.remisiones.pull.dto.RemisionRowDto;
import com.mh.rfid.integraciones.remisiones.pull.service.api.RemisionesLoadService;
import com.mh.rfid.repository.esb.RemisionRepository;

import lombok.val;

@Service
public class RemisionesLoadServiceImpl extends LoadServiceImpl<RemisionRowDto, Remision>
		implements RemisionesLoadService {

	@Autowired
	private RowDao<RemisionRowDto> rowDao;

	@Autowired
	private RemisionRepository repository;

	@Override
	public IntegracionType getIntegracionType() {
		return IntegracionType.REMISIONES;
	}

	@Override
	protected RowDao<RemisionRowDto> getRowDao() {
		return rowDao;
	}

	@Override
	protected RemisionRepository getRepository() {
		return repository;
	}

	@Override
	protected Remision getNewEntity() {
		return new Remision();
	}

	@Override
	protected void mapRowsToEntity(List<RemisionRowDto> rows, Remision entity) {
		super.mapRowsToEntity(rows, entity);

		val row = rows.get(0);
		entity.setOrderNumber(row.getOrderNumber());
		entity.setRemissionDate(row.getRemissionDate());
		entity.setDestinationCode(row.getDestinationCode());
		entity.setDestinationId(row.getDestinationId());
	}
}
