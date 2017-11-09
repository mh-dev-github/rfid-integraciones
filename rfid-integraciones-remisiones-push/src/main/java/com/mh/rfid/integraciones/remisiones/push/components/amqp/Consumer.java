package com.mh.rfid.integraciones.remisiones.push.components.amqp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mh.rfid.core.push.components.amqp.PushConsumer;
import com.mh.rfid.core.push.service.api.PushErrorReportService;
import com.mh.rfid.core.push.service.api.PushService;
import com.mh.rfid.core.push.service.api.VerificationService;
import com.mh.rfid.domain.esb.Remision;
import com.mh.rfid.integraciones.remisiones.push.service.api.RemisionesPushErrorReportService;
import com.mh.rfid.integraciones.remisiones.push.service.api.RemisionesPushService;
import com.mh.rfid.integraciones.remisiones.push.service.api.RemisionesVerificactionService;

@Component
public class Consumer extends PushConsumer<Remision> {

	@Autowired
	private Producer producer;

	@Autowired
	private RemisionesPushService pushService;

	@Autowired
	private RemisionesVerificactionService verificactionService;

	@Autowired
	private RemisionesPushErrorReportService errorReportService;

	@Override
	protected Producer getProducer() {
		return producer;
	}

	@Override
	protected PushService<Remision> getPushService() {
		return pushService;
	}

	@Override
	protected VerificationService<Remision> getVerificationService() {
		return verificactionService;
	}
	
	@Override
	protected PushErrorReportService getErrorReportService() {
		return errorReportService;
	}
}