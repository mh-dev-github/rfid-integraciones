package com.mh.rfid.integraciones.remisiones.pull.components.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.mh.rfid.core.pull.components.dao.RowDaoImpl;
import com.mh.rfid.enums.EstadoRowType;
import com.mh.rfid.enums.OperacionType;
import com.mh.rfid.integraciones.remisiones.pull.dto.RemisionRowDto;

import lombok.val;

@Component
public class RemisionRowDao extends RowDaoImpl<RemisionRowDto> {

	@Autowired
	public NamedParameterJdbcTemplate stageJdbcTemplate;

	@Override
	protected NamedParameterJdbcTemplate getStageJdbcTemplate() {
		return stageJdbcTemplate;
	}

	@Override
	protected RowMapper<RemisionRowDto> getRowMapper() {
		return (rs, rowNum) -> {
			val fechaTransformacion = rs.getTimestamp("fechaTransformacion");
			val fechaCargue = rs.getTimestamp("fechaCargue");

			// @formatter:off
			val result = RemisionRowDto
				.builder()
				.id(rs.getLong("id"))
				.secuencia(rs.getLong("secuencia"))
				.externalId(rs.getString("externalId"))
				.operacion(OperacionType.valueOf(rs.getString("operacion")))
				.estado(EstadoRowType.valueOf(rs.getString("estado")))

				.fechaUltimoCambioEnOrigen(rs.getTimestamp("fechaUltimoCambioEnOrigen").toLocalDateTime())
				.fechaExtraccion(rs.getTimestamp("fechaExtraccion").toLocalDateTime())
				.fechaTransformacion((fechaTransformacion == null) ? null : fechaTransformacion.toLocalDateTime())
				.fechaCargue((fechaCargue == null) ? null : fechaCargue.toLocalDateTime())

				.orderNumber(rs.getString("orderNumber"))
				.remissionDate(rs.getString("remissionDate"))
				.destinationCode(rs.getString("destinationCode"))
				.destinationId(rs.getString("destinationId"))
				
				.build();
			// @formatter:on

			return result;
		};
	}

	@Override
	protected String getSqlSelectFromStage() {
		// @formatter:off
		val result = "" 
		+" SELECT  "
		+"      id "
		+"     ,secuencia "
		+"     ,externalId "
		+"     ,operacion "
		+"     ,estado "
		+"     ,fechaUltimoCambioEnOrigen "
		+"     ,fechaExtraccion "
		+"     ,fechaTransformacion "
		+"     ,fechaCargue "
		+" "
		+"     ,orderNumber "
		+"     ,remissionDate "
		+"     ,destinationCode "
		+"     ,destinationId "
		+" FROM stage.Remisiones "
		+" WHERE "
		+"     secuencia = :secuencia "
		+" ORDER BY "
		+"     id  "
		+ "  ";
		// @formatter:on

		return result;
	}

	@Override
	protected String getSqlInsertIntoStage() {
		// @formatter:off
		val result = "" 
		+ " INSERT INTO stage.Remisiones" 
		+ "    (secuencia,externalId,operacion,estado,orderNumber,remissionDate,destinationCode,destinationId,fechaUltimoCambioEnOrigen,fechaExtraccion,fechaTransformacion,fechaCargue)"
		+ "	VALUES" 
		+ "    (:secuencia,:externalId,:operacion,:estado,:orderNumber,:remissionDate,:destinationCode,:destinationId,:fechaUltimoCambioEnOrigen,:fechaExtraccion,:fechaTransformacion,:fechaCargue)";		
		// @formatter:on
		return result;
	}

	@Override
	protected String getSqlUpdateTransformedRows() {
		// @formatter:off
		val result = "" 
		+"  "
		+" UPDATE stage.Remisiones  "
		+" SET  estado = :estado "
		+"     ,destinationId = :destinationId "
		+"     ,fechaTransformacion = :fechaTransformacion  "
		+" WHERE  "
		+"     id = :id "
		+"  "
		+ " ";
		// @formatter:on
		return result;
	}

	@Override
	protected String getSqlUpdateLoadedRows() {
		// @formatter:off
		val result = "" 
		+"  "
		+" UPDATE stage.Remisiones  "
		+" SET  estado = :estado "
		+"     ,fechaCargue = :fechaCargue  "
		+" WHERE  "
		+"     id = :id "
		+"  "
		+ " ";
		// @formatter:on
		return result;
	}

	@Override
	protected String getSqlDeleteFromStage() {
		val result = "DELETE FROM stage.Remisiones WHERE id = :id";
		return result;
	}

	@Override
	protected String getSqlSelectSequencesWithFixedRowsFromStage() {
		val result = "SELECT DISTINCT a.secuencia FROM stage.Remisiones a WHERE estado = :estado";
		return result;
	}
}
