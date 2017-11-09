package com.mh.rfid.integraciones.ventas.pull.components.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.mh.rfid.core.pull.components.dao.RowDaoImpl;
import com.mh.rfid.enums.EstadoRowType;
import com.mh.rfid.enums.OperacionType;
import com.mh.rfid.integraciones.ventas.pull.dto.VentaRowDto;

import lombok.val;

@Component
public class VentaRowDao extends RowDaoImpl<VentaRowDto> {

	@Autowired
	public NamedParameterJdbcTemplate stageJdbcTemplate;

	@Override
	protected NamedParameterJdbcTemplate getStageJdbcTemplate() {
		return stageJdbcTemplate;
	}

	@Override
	protected RowMapper<VentaRowDto> getRowMapper() {
		return (rs, rowNum) -> {
			val fechaTransformacion = rs.getTimestamp("fechaTransformacion");
			val fechaCargue = rs.getTimestamp("fechaCargue");

			// @formatter:off
			val result = VentaRowDto
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

				.storeCode(rs.getString("storeCode"))
				.saleDate(rs.getString("saleDate"))
				.barCode(rs.getString("barCode"))
				.quantity(rs.getInt("quantity"))
				
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
		+"     ,storeCode "
		+"     ,saleDate "
		+"     ,barCode "
		+"     ,quantity "
		+" FROM stage.Ventas "
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
		+ " INSERT INTO stage.Ventas" 
		+ "    (secuencia,externalId,operacion,estado,storeCode,saleDate,barCode,productId,quantity,fechaUltimoCambioEnOrigen,fechaExtraccion,fechaTransformacion,fechaCargue)"
		+ "	VALUES" 
		+ "    (:secuencia,:externalId,:operacion,:estado,:storeCode,:saleDate,:barCode,:productId,:quantity,:fechaUltimoCambioEnOrigen,:fechaExtraccion,:fechaTransformacion,:fechaCargue)";		
		// @formatter:on
		return result;
	}
	
	@Override
	protected String getSqlUpdateTransformedRows() {
		// @formatter:off
		val result = "" 
		+"  "
		+" UPDATE stage.Ventas  "
		+" SET  estado = :estado "
		+"     ,productId = :productId "
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
		+" UPDATE stage.Ventas  "
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
		val result = "DELETE FROM stage.Ventas WHERE id = :id";
		return result;
	}

	@Override
	protected String getSqlSelectSequencesWithFixedRowsFromStage() {
		val result = "SELECT DISTINCT a.secuencia FROM stage.Ventas a WHERE estado = :estado";
		return result;
	}
}