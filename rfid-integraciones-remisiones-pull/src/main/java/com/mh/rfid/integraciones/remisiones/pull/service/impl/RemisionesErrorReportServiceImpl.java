package com.mh.rfid.integraciones.remisiones.pull.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.mh.rfid.core.pull.service.impl.PullErrorReportServiceImpl;
import com.mh.rfid.enums.IntegracionType;
import com.mh.rfid.integraciones.remisiones.pull.configuration.DatabaseConfiguration;
import com.mh.rfid.integraciones.remisiones.pull.service.api.RemisionesErrorReportService;

import lombok.val;

@Component
public class RemisionesErrorReportServiceImpl extends PullErrorReportServiceImpl implements RemisionesErrorReportService {

	@Qualifier(DatabaseConfiguration.JDBC_TEMPLATE)
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	protected NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public IntegracionType getIntegracionType() {
		return IntegracionType.REMISIONES;
	}

	@Override
	protected List<String> getHeaders() {
		// @formatter:off
		String[] headers = { 
				"Id", 
				"Secuencia",
				"External ID", 
				"Operación", 
				"Estado", 
				
				"Código Error", 
				"Mensaje", 
				"Fecha/Hora",
				
				"order Number", 
				"Remission Date",
				"Destination Code",
				"Quantity", 
				
				"Último Cambio En Origen", 
				"Fecha Extracción", 
				"Fecha Transformación",
				};
		// @formatter:on

		val result = Arrays.asList(headers);
		return result;
	}

	@Override
	protected String getSqlSelect() {
		// @formatter:off
		val result = "" 
			    + " SELECT  "
			    + "      a.id  "
			    + "     ,a.secuencia  "
			    + "     ,a.externalId  "
			    + "     ,a.operacion  "
			    + "     ,a.estado  "
			    + "     ,b.codigo  "
			    + "     ,b.mensaje  "
			    + "     ,b.fechaCreacion  "
			    + "     ,a.orderNumber  "
			    + "     ,a.remissionDate  "
			    + "     ,a.destinationCode  "
			    + "     ,a.fechaUltimoCambioEnOrigen  "
			    + "     ,a.fechaExtraccion  "
			    + "     ,a.fechaTransformacion  "
			    + " FROM stage.Remisiones a  "
			    + " INNER JOIN stage.Errores b ON  "
			    + "     b.id = a.id  "
			    + " WHERE  "
			    + "     a.secuencia = :secuencia  "
			    + " ORDER BY  "
			    + "     a.externalId  "
			    + "    ,b.idError  "
				+ "  ";
		// @formatter:on

		return result;
	}

	@Override
	protected RowMapper<List<String>> getRowMapper() {
		// @formatter:off
		return (rs, rowNum) -> {
			val result = new ArrayList<String>();
			Timestamp fecha;

			result.add(rs.getString("id"));
			result.add(rs.getString("secuencia"));
			result.add(rs.getString("externalId"));
			result.add(rs.getString("operacion"));
			result.add(rs.getString("estado"));
			result.add(rs.getString("codigo"));
			result.add(rs.getString("mensaje"));
			
			fecha = rs.getTimestamp("fechaCreacion");
			result.add(getFormatterDateTime().format(fecha.toLocalDateTime()));
			
			result.add(rs.getString("orderNumber"));
			result.add(rs.getString("remissionDate"));
			result.add(rs.getString("destinationCode"));

			fecha = rs.getTimestamp("fechaUltimoCambioEnOrigen");
			result.add(getFormatterDateTime().format(fecha.toLocalDateTime()));

			fecha = rs.getTimestamp("fechaExtraccion");
			result.add(getFormatterDateTime().format(fecha.toLocalDateTime()));

			fecha = rs.getTimestamp("fechaTransformacion");
			result.add((fecha == null)?"":getFormatterDateTime().format(fecha.toLocalDateTime()));
						
			return result;
		};
		// @formatter:on
	}
}
