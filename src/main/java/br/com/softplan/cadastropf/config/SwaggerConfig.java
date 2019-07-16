/*
* Copyright 2018 Platform Builders
*************************************************************
*Nome     : SwaggerConfiguration.java
*Autor    : tiagocanatelli
*Data     : 15/02/2018
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.softplan.cadastropf.config;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig é uma classe de configuração do Swagger.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket swaggerApi() {
    return new Docket(SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .paths(any()).build().pathMapping("/");
  }
}
