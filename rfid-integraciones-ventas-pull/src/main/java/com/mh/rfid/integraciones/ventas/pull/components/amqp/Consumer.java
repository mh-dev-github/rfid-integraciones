package com.mh.rfid.integraciones.ventas.pull.components.amqp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mh.rfid.core.pull.components.amqp.PullConsumer;
import com.mh.rfid.core.pull.service.api.ExtractionService;
import com.mh.rfid.core.pull.service.api.LoadService;
import com.mh.rfid.core.pull.service.api.PullErrorReportService;
import com.mh.rfid.core.pull.service.api.TransformationService;
import com.mh.rfid.domain.esb.Venta;
import com.mh.rfid.integraciones.ventas.pull.dto.VentaRowDto;
import com.mh.rfid.integraciones.ventas.pull.service.api.VentasErrorReportService;
import com.mh.rfid.integraciones.ventas.pull.service.api.VentasExtractionService;
import com.mh.rfid.integraciones.ventas.pull.service.api.VentasLoadService;
import com.mh.rfid.integraciones.ventas.pull.service.api.VentasTransformationService;

@Component
public class Consumer extends PullConsumer<VentaRowDto, Venta> {

	@Autowired
	private Producer producer;

	@Autowired
	private VentasExtractionService extractionService;

	@Autowired
	private VentasTransformationService transformationService;

	@Autowired
	private VentasLoadService loadService;

	@Autowired
	private VentasErrorReportService errorReportService;

	@Override
	protected Producer getProducer() {
		return producer;
	}

	@Override
	protected ExtractionService<VentaRowDto> getExtractionService() {
		return extractionService;
	}

	@Override
	protected TransformationService<VentaRowDto> getTransformationService() {
		return transformationService;
	}

	@Override
	protected LoadService<VentaRowDto, Venta> getLoadService() {
		return loadService;
	}

	@Override
	protected PullErrorReportService getErrorReportService() {
		return errorReportService;
	}
}