package com.mh.rfid.integraciones.remisiones.pull.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mh.rfid.integraciones.remisiones.pull.service.api.LookUpService;
import com.mh.rfid.repository.esb.LocacionRepository;

import lombok.val;

@Service
public class LookUpServiceImpl implements LookUpService {

	public static final String CODIGO_ERROR_DESTINATION_CODE = "error_destination_code";

	public static final String MENSAJE_ERROR_DESTINATION_CODE = "Código destinación %s no existe";

	@Autowired
	LocacionRepository locacionRepository;

	@Override
	@Cacheable(cacheNames="locaciones")
	public String translateDestinationCode(String destinationCode) {
		val result = locacionRepository.translateCode(destinationCode);
		return result;
	}
}