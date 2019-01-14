package com.example.demo;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket usersApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(usersApiInfo()).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				// .paths(PathSelectors.ant("/greeting/**"))
				.paths(PathSelectors.any()).build().useDefaultResponseMessages(false);

	}

	private ApiInfo usersApiInfo() {
		return new ApiInfoBuilder().title("Microservicio Greeting").version("1.0").license("Apache License Version 2.0")
				.build();

	}

	private Predicate<String> userPaths() {
		return regex("/greeting.*");
	}
}
