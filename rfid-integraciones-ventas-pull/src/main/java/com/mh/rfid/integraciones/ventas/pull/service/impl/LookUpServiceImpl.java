package com.mh.rfid.integraciones.ventas.pull.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mh.rfid.integraciones.ventas.pull.service.api.LookUpService;
import com.mh.rfid.repository.esb.LocacionRepository;
import com.mh.rfid.repository.esb.ProductoRepository;

import lombok.val;

@Service
public class LookUpServiceImpl implements LookUpService {

	public static final String CODIGO_ERROR_PRODUCT_CODE = "error_product_code";

	public static final String MENSAJE_ERROR_PRODUCT_CODE = "Código producto %s no existe";

	@Autowired
	LocacionRepository locacionRepository;

	@Autowired
	ProductoRepository productoRepository;

	@Override
	@Cacheable(cacheNames="productos")
	public String translateProductCode(String barCode) {
		System.out.println(barCode);
		val result = productoRepository.translateCode(barCode);
		return result;
	}
}