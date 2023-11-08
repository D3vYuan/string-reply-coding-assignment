package com.beta.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any()).build();

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.beta.controller")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact("XiaoBudian", "https://github.com/D3vYuan", "xiaobudian@xample.com");
		return new ApiInfo("String Reply API", "The most awesome API in town", "Version 1.0",
				"http://localhost:8080/swagger-ui/#/", contact, "API License", "http://localhost:8080/swagger-ui/#/",
				Collections.emptyList());
	}
}