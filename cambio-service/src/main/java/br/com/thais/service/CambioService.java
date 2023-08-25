package br.com.thais.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.thais.model.Cambio;
import br.com.thais.repository.CambioRepository;

@Service
public class CambioService {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CambioRepository repository;
	
	public Cambio getCambio(BigDecimal amount, String from, String to) {
		var response = repository.findByFromAndTo(from, to);
		if (!response.isPresent()) throw new RuntimeException("Currency Unsupported");
		
		var cambio = mapCambio(amount, response.get());
		return cambio;
		
	}

	private Cambio mapCambio(BigDecimal amount, Cambio cambio) {
		var port = environment.getProperty("local.server.port");	
		BigDecimal conversionFactor = cambio.getConversionFactor();	
		BigDecimal convertedValue = conversionFactor.multiply(amount);		
		cambio.setConvertedValue(formatValue(convertedValue));
		cambio.setEnvironment(port);
		return cambio;
	}

	private BigDecimal formatValue(BigDecimal convertedValue) {
		return convertedValue.setScale(2, RoundingMode.CEILING);
	}
	
}
