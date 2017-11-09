package com.mh.rfid.integraciones.remisiones.pull.dto;

import java.time.LocalDateTime;

import com.mh.rfid.dto.RowDto;
import com.mh.rfid.enums.EstadoRowType;
import com.mh.rfid.enums.OperacionType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class RemisionRowDto extends RowDto {

	private static final long serialVersionUID = 1L;

	private String orderNumber;

	private String remissionDate;

	private String destinationCode;

	private String destinationId;

	@Builder
	private RemisionRowDto(Long id, Long secuencia, String externalId, OperacionType operacion, EstadoRowType estado,
			LocalDateTime fechaUltimoCambioEnOrigen, LocalDateTime fechaExtraccion, LocalDateTime fechaTransformacion,
			LocalDateTime fechaCargue, String orderNumber, String remissionDate, String destinationCode,
			String destinationId) {
		super(id, secuencia, externalId, operacion, estado, fechaUltimoCambioEnOrigen, fechaExtraccion,
				fechaTransformacion, fechaCargue);
		this.orderNumber = orderNumber;
		this.remissionDate = remissionDate;
		this.destinationCode = destinationCode;
		this.destinationId = destinationId;
	}
}