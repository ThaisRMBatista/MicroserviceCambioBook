package br.com.thais.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.thais.model.Book;
import br.com.thais.proxy.CambioProxy;
import br.com.thais.repository.BookRepository;
import br.com.thais.response.Cambio;

@Service
public class BookService {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CambioProxy proxy;
	
	public Book findBook(Long id, String currency) {
		
		var optionalBook = repository.findById(id);
		if(!optionalBook.isPresent()) {
			throw new RuntimeException("Book not found");
		}
		
		var book = optionalBook.get();
		var cambio = getCambioResponse(book, currency);		
		var port = environment.getProperty("local.server.port");
		
		book.setEnvironment(port + "FEIGN");
		book.setPrice(cambio.getConvertedValue());
		return book;
		
	}
	
	public Cambio getCambioResponseSemFeign(Book book, String currency) {
		//Forma de consumir outro microsserviço sem usar o FEIGN
		HashMap<String, String> params = new HashMap<>();
		params.put("amount", book.getPrice().toString());
		params.put("from", "USD");
		params.put("to", currency);
		
		var response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}", 
				Cambio.class, 
				params);
		
		return response.getBody();
	}
	
	public Cambio getCambioResponse(Book book, String currency) {	
		return proxy.getCambio(book.getPrice(), "USD", currency);
	}
}
