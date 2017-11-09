package com.mh.rfid.integraciones.ventas.pull.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.rfid.core.pull.components.dao.RowDao;
import com.mh.rfid.core.pull.service.impl.LoadServiceImpl;
import com.mh.rfid.domain.esb.Venta;
import com.mh.rfid.domain.esb.Venta.Linea;
import com.mh.rfid.enums.IntegracionType;
import com.mh.rfid.integraciones.ventas.pull.dto.VentaRowDto;
import com.mh.rfid.integraciones.ventas.pull.service.api.VentasLoadService;
import com.mh.rfid.repository.esb.VentaRepository;

import lombok.val;

@Service
public class VentasLoadServiceImpl extends LoadServiceImpl <VentaRowDto,Venta> implements VentasLoadService {

	@Autowired
	private RowDao<VentaRowDto> rowDao;

	@Autowired
	private VentaRepository repository;
	
	@Override
	public IntegracionType getIntegracionType() {
		return IntegracionType.VENTAS;
	}

	@Override
	protected RowDao<VentaRowDto> getRowDao() {
		return rowDao;
	}

	@Override
	protected VentaRepository getRepository() {
		return repository;
	}

	@Override
	protected Venta getNewEntity() {
		return new Venta();
	}

	@Override
	protected void mapRowsToEntity(List<VentaRowDto> rows, Venta entity) {
		super.mapRowsToEntity(rows, entity);
		
		{
			val row = rows.get(0);

			entity.setStoreCode(row.getStoreCode());
			entity.setSaleDate(row.getSaleDate());
		}
		
		rows.forEach(row -> {
			val item = new Linea();
			item.setBarCode(row.getBarCode());
			item.setQuantity(row.getQuantity());
			entity.getLineas().add(item);
		});
	}
}
