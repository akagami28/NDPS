package com.ndps.ots.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder()
				.title("Demo Application Api Documentation")
				.contact(new Contact("DEV-Team", "https://www.nttdatapay.com/", "akhil@ndps"))
				.license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.version("1.0.0")
				.build();
	}
	
	/**
	 * A docket is an object that contains all the customizable properties you set and is used by 
	 * Swagger to generate documentation of REST APIs for RESTful Web services via a web browser
	 * @return Docket
	 */
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2)
			
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ndps.ots.controller"))
//				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build().apiInfo(apiInfo());
	}
}
