package com.mh.rfid.integraciones.remisiones.pull.service.impl;

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
import com.mh.rfid.integraciones.remisiones.pull.configuration.SourceDatabaseConfiguration;
import com.mh.rfid.integraciones.remisiones.pull.dto.RemisionRowDto;
import com.mh.rfid.integraciones.remisiones.pull.service.api.RemisionesExtractionService;

import lombok.val;

@Service
public class RemisionesExtractionServiceImpl extends ExtractionServiceImpl<RemisionRowDto>
		implements RemisionesExtractionService {

	@Qualifier(SourceDatabaseConfiguration.JDBC_TEMPLATE)
	@Autowired
	private NamedParameterJdbcTemplate sourceJdbcTemplate;

	@Autowired
	private RowDao<RemisionRowDto> rowDao;

	// -------------------------------------------------------------------------------------
	// IntegracionType
	// -------------------------------------------------------------------------------------
	@Override
	public IntegracionType getIntegracionType() {
		return IntegracionType.REMISIONES;
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
	protected RowDao<RemisionRowDto> getRowDao() {
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
				+ " 	,orderNumber " 
				+ " 	,remissionDate "
				+ " 	,destinationCode " 
				+ " 	,operacion " 
				+ " 	,fechaActualizacion " 
				+ " FROM stage.Remisiones a " 
				+ " WHERE "
				+ " 	a.fechaActualizacion >= :fechaDesde "
				+ " AND	a.fechaActualizacion < :fechaHasta "
				+ " ORDER BY "
				+ " 	 fechaActualizacion "
				+ " 	,externalId "
				+ "  ";
		// @formatter:on

		return result;
	}

	@Override
	protected RowMapper<RemisionRowDto> getRowMapper() {
		// @formatter:off
		final LocalDateTime now = LocalDateTime.now();
		
		return (rs, rowNum) -> {
			val result = RemisionRowDto
				.builder()
				.externalId(rs.getString("externalId"))
				.operacion(OperacionType.valueOf(rs.getString("operacion")))
				.estado(EstadoRowType.ESTRUCTURA_VALIDA)

				.fechaUltimoCambioEnOrigen(rs.getTimestamp("fechaActualizacion").toLocalDateTime())
				.fechaExtraccion(now)

				.orderNumber(rs.getString("orderNumber"))
				.remissionDate(rs.getString("remissionDate"))
				.destinationCode(rs.getString("destinationCode"))

				.build();
			return result;
		};
		// @formatter:on
	}
}