package br.com.thais.configuration;

import org.hibernate.cfg.annotations.Nullability;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

@Configuration
@OpenAPIDefinition(info = 
@Info(title = "Cambio Service API",
	version = "v1",
	description = "Documentation of Cambio Service API"))
public class OpenApiConfiguration {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.info(new io.swagger.v3.oas.models.info.Info()
			.title("Cambio Service API")
			.version("v1")
			.description("Documentation of Cambio Service API")
			.license(new License()
						.name("Apache 2.0")
						.url("http://springdoc.org"))
		    .description("SpringShop Wiki Documentation"));
	}
}