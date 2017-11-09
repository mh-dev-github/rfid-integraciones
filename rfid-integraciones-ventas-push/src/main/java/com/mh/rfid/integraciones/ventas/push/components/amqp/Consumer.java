package com.mh.rfid.integraciones.ventas.push.components.amqp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mh.rfid.core.push.components.amqp.PushConsumer;
import com.mh.rfid.core.push.service.api.PushErrorReportService;
import com.mh.rfid.core.push.service.api.PushService;
import com.mh.rfid.core.push.service.api.VerificationService;
import com.mh.rfid.domain.esb.Venta;
import com.mh.rfid.integraciones.ventas.push.service.api.VentasPushErrorReportService;
import com.mh.rfid.integraciones.ventas.push.service.api.VentasPushService;

@Component
public class Consumer extends PushConsumer<Venta> {

	@Autowired
	private Producer producer;

	@Autowired
	private VentasPushService pushService;
	
	@Autowired
	private VentasPushErrorReportService errorReportService;

	@Override
	protected Producer getProducer() {
		return producer;
	}

	@Override
	protected PushService<Venta> getPushService() {
		return pushService;
	}

	@Override
	protected PushErrorReportService getErrorReportService() {
		return errorReportService;
	}

	@Override
	protected VerificationService<Venta> getVerificationService() {
		return null;
	}
}