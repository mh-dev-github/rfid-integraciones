package com.mh.rfid.integraciones.remisiones.pull.components.amqp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mh.rfid.core.pull.components.amqp.PullConsumer;
import com.mh.rfid.core.pull.service.api.ExtractionService;
import com.mh.rfid.core.pull.service.api.LoadService;
import com.mh.rfid.core.pull.service.api.PullErrorReportService;
import com.mh.rfid.core.pull.service.api.TransformationService;
import com.mh.rfid.domain.esb.Remision;
import com.mh.rfid.integraciones.remisiones.pull.dto.RemisionRowDto;
import com.mh.rfid.integraciones.remisiones.pull.service.api.RemisionesErrorReportService;
import com.mh.rfid.integraciones.remisiones.pull.service.api.RemisionesExtractionService;
import com.mh.rfid.integraciones.remisiones.pull.service.api.RemisionesLoadService;
import com.mh.rfid.integraciones.remisiones.pull.service.api.RemisionesTransformationService;

@Component
public class Consumer extends PullConsumer<RemisionRowDto, Remision> {

	@Autowired
	private Producer producer;

	@Autowired
	private RemisionesExtractionService extractionService;

	@Autowired
	private RemisionesTransformationService transformationService;

	@Autowired
	private RemisionesLoadService loadService;

	@Autowired
	private RemisionesErrorReportService errorReportService;

	@Override
	protected Producer getProducer() {
		return producer;
	}

	@Override
	protected ExtractionService<RemisionRowDto> getExtractionService() {
		return extractionService;
	}

	@Override
	protected TransformationService<RemisionRowDto> getTransformationService() {
		return transformationService;
	}

	@Override
	protected LoadService<RemisionRowDto, Remision> getLoadService() {
		return loadService;
	}

	@Override
	protected PullErrorReportService getErrorReportService() {
		return errorReportService;
	}
}