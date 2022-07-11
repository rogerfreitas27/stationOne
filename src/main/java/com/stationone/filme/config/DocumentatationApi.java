package com.stationone.filme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class DocumentatationApi {

	
	@Bean
	  public OpenAPI springShopOpenAPI() {
	      return new OpenAPI()
	              .info(new Info().title("Documentação Api Filme")
	              .description("Documentação para uma api de filme, desenvolvido para staionOne")
	              .version("v0.0.1")
	              .license(new License().name("Apache 2.0").url("http://springdoc.org")))
	              .externalDocs(new ExternalDocumentation()
	              .description("Desenvolvido para aprimorar os conhecimentos estudados")
	              .url("rogerdesenvolvedorweb.com.br"));
	  }
	
	
}
