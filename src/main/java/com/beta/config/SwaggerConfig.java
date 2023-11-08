package com.beta.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beta.constant.SwaggerConstant;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(SwaggerConstant.BASE_PACKAGE)).paths(PathSelectors.any())
				.build().apiInfo(apiInfo()).securitySchemes(securitySchemes()).securityContexts(securityContexts());
	}

	private List<SecurityScheme> securitySchemes() {
		List<SecurityScheme> schemeList = new ArrayList<>();
		schemeList.add(new BasicAuth(SwaggerConstant.BASIC_AUTH_SCHEME));
		return schemeList;
	}

	private List<SecurityContext> securityContexts() {
		List<SecurityContext> contextList = new ArrayList<>();
		SecurityContext basicAuthContext = SecurityContext.builder()
				.securityReferences(Arrays.asList(basicAuthReference())).operationSelector(operationContext -> true)
				.build();
		contextList.add(basicAuthContext);
		return contextList;
	}

	private SecurityReference basicAuthReference() {
		return new SecurityReference(SwaggerConstant.BASIC_AUTH_SCHEME, new AuthorizationScope[0]);
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact(SwaggerConstant.CONTACT_NAME, SwaggerConstant.CONTACT_URL,
				SwaggerConstant.CONTACT_EMAIL);
		return new ApiInfo(SwaggerConstant.API_TITLE, SwaggerConstant.API_DESCRIPTION, SwaggerConstant.API_VERSION,
				SwaggerConstant.API_TOS_URL, contact, SwaggerConstant.API_LICENSE_TITLE,
				SwaggerConstant.API_LICENSE_URL, Collections.emptyList());
	}
}