package com.application.demo.configs;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.ndps.ots.swagger.config.SwaggerConfig;
import com.ndps.ots.utility.Constant;

import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerTests {
	
	@Test
	void testSwaggerConfig()
	{
		SwaggerConfig swaggerConfig = new SwaggerConfig();
		//Docket docket = new Docket(DocumentationType.SWAGGER_2);
		assertThat(swaggerConfig.api()).isExactlyInstanceOf(Docket.class);
	}
	
	@Test
	void testConstant()
	{
		@SuppressWarnings("unused")
		Constant constant = new Constant();
		
				
	}

}
