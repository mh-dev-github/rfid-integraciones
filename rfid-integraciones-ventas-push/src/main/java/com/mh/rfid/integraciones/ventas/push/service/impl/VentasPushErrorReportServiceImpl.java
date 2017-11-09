package com.mh.rfid.integraciones.ventas.push.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.mh.rfid.core.push.service.impl.PushErrorReportServiceImpl;
import com.mh.rfid.enums.IntegracionType;
import com.mh.rfid.integraciones.ventas.push.configuration.DatabaseConfiguration;
import com.mh.rfid.integraciones.ventas.push.service.api.VentasPushErrorReportService;

@Component
public class VentasPushErrorReportServiceImpl extends PushErrorReportServiceImpl
		implements VentasPushErrorReportService {

	@Qualifier(DatabaseConfiguration.JDBC_TEMPLATE)
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	protected NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public IntegracionType getIntegracionType() {
		return IntegracionType.VENTAS;
	}
}
