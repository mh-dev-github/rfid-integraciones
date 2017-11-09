package com.mh.rfid.integraciones.ventas.pull.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.mh.rfid.core.pull.components.dao.RowDao;
import com.mh.rfid.core.pull.service.impl.ExtractionServiceImpl;
import com.mh.rfid.enums.EstadoRowType;
import com.mh.rfid.enums.IntegracionType;
import com.mh.rfid.enums.OperacionType;
import com.mh.rfid.integraciones.ventas.pull.configuration.SourceDatabaseConfiguration;
import com.mh.rfid.integraciones.ventas.pull.dto.VentaRowDto;
import com.mh.rfid.integraciones.ventas.pull.service.api.VentasExtractionService;

import lombok.val;

@Service
public class VentasExtractionServiceImpl extends ExtractionServiceImpl<VentaRowDto> implements VentasExtractionService {

	@Qualifier(SourceDatabaseConfiguration.JDBC_TEMPLATE)
	@Autowired
	private NamedParameterJdbcTemplate sourceJdbcTemplate;

	@Autowired
	private RowDao<VentaRowDto> rowDao;

	// -------------------------------------------------------------------------------------
	// IntegracionType
	// -------------------------------------------------------------------------------------
	@Override
	public IntegracionType getIntegracionType() {
		return IntegracionType.VENTAS;
	}

	// -------------------------------------------------------------------------------------
	// JDC Templates
	// -------------------------------------------------------------------------------------
	@Override
	protected NamedParameterJdbcTemplate getSourceJdbcTemplate() {
		return sourceJdbcTemplate;
	}

	// -------------------------------------------------------------------------------------
	// DAO
	// -------------------------------------------------------------------------------------
	@Override
	protected RowDao<VentaRowDto> getRowDao() {
		return rowDao;
	}

	// -------------------------------------------------------------------------------------
	// Extract
	// -------------------------------------------------------------------------------------
	@Override
	protected String getSqlSelectFromSource() {
		// @formatter:off
		val result = "" 
		+ " SELECT "
		+ " 	 externalId "
		+ " 	,storeCode "
		+ " 	,saleDate "
		+ " 	,barCode "
		+ " 	,quantity "
		+ " 	,operacion "
		+ " 	,fechaActualizacion "
		+ " FROM stage.Ventas a "
		+ " WHERE "
		+ " 	a.fechaActualizacion >= :fechaDesde "
		+ " AND	a.fechaActualizacion < :fechaHasta "
		+ " AND	a.quantity <> 0 "
		+ " ORDER BY "
		+ " 	 fechaActualizacion "
		+ " 	,externalId "
		+ " 	,barCode "
		+ "  ";		
		// @formatter:on

		return result;
	}

	@Override
	protected RowMapper<VentaRowDto> getRowMapper() {
		// @formatter:off
		final LocalDateTime now = LocalDateTime.now();
		
		return (rs, rowNum) -> {
			VentaRowDto result = VentaRowDto
				.builder()
				.externalId(rs.getString("externalId"))
				.operacion(OperacionType.valueOf(rs.getString("operacion")))
				.estado(EstadoRowType.ESTRUCTURA_VALIDA)

				.fechaUltimoCambioEnOrigen(rs.getTimestamp("fechaActualizacion").toLocalDateTime())
				.fechaExtraccion(now)
				
				.storeCode(rs.getString("storeCode"))
				.saleDate(rs.getString("saleDate"))
				.barCode(rs.getString("barCode"))
				.quantity(rs.getInt("quantity"))

				.build();
			return result;
		};
		// @formatter:on
	}
}