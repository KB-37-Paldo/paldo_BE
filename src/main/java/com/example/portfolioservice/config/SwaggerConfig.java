package com.example.portfolioservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	 @Bean
	    public Docket api() {
		 return new Docket(DocumentationType.OAS_30) // open api spec 3.0
				 .select()
				 .apis(RequestHandlerSelectors.any())
				 .paths(PathSelectors.any())
				 .build()
				 .apiInfo(apiInfo());
	    }

	    
	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title( "Portfolio Service Swagger")
	                .description("MoneyMany의 백엔드 포트폴리오 서비스 API 명세")
	                .version("1.0")
	                .build();
	    }
}