package com.mh.rfid.integraciones.ventas.pull.dto;

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
public class VentaRowDto extends RowDto {

	private static final long serialVersionUID = 1L;

	private String storeCode;

	private String saleDate;

	private String barCode;
	
	private String productId;

	private int quantity;

	@Builder
	private VentaRowDto(Long id, Long secuencia, String externalId, OperacionType operacion, EstadoRowType estado,
			LocalDateTime fechaUltimoCambioEnOrigen, LocalDateTime fechaExtraccion, LocalDateTime fechaTransformacion,
			LocalDateTime fechaCargue, String storeCode, String saleDate, String barCode, String productId,
			int quantity) {
		super(id, secuencia, externalId, operacion, estado, fechaUltimoCambioEnOrigen, fechaExtraccion,
				fechaTransformacion, fechaCargue);
		this.storeCode = storeCode;
		this.saleDate = saleDate;
		this.barCode = barCode;
		this.productId = productId;
		this.quantity = quantity;
	}
}
