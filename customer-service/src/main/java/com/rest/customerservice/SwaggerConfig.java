package com.rest.customerservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.rest.customerservice.controller"))
				.paths(PathSelectors.regex("/.*"))
				.build();
	}
	
	public ApiInfo custInfo()
    {
       ApiInfo apiInfo = new ApiInfo(
               "Product Service Management",  // Title
               "Product Service",      // Description 
               "1.0",                   // Version
               "TOS",                   // Terms of Service
               new Contact("SK", "//sk.com/springBoot-mvc/", "test@test.com"), // Contact
               "JIPlicense",            // License
               "//sk.com/"); //License URL
       
        return apiInfo;
    }
}
