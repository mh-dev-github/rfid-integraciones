package com.mh.rfid.integraciones.remisiones.push.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.mh.rfid.core.push.service.impl.PushErrorReportServiceImpl;
import com.mh.rfid.enums.IntegracionType;
import com.mh.rfid.integraciones.remisiones.push.configuration.DatabaseConfiguration;
import com.mh.rfid.integraciones.remisiones.push.service.api.RemisionesPushErrorReportService;

@Component
public class RemisionesPushErrorReportServiceImpl extends PushErrorReportServiceImpl
		implements RemisionesPushErrorReportService {

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
}
