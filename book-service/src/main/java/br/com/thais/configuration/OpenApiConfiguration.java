package br.com.thais.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfiguration {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components())
				.info(
						new io.swagger.v3.oas.models.info.Info()
						.title("Book Service API")
						.description("Documentation of Book Service API")
						.version("v1")
						.license(new License()
								.name("Apache 2.0")
								.url("http://springdoc.org")
								)
						);
	}
	
}
