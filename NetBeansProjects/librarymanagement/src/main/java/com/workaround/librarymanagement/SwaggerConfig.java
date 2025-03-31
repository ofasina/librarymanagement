/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.librarymanagement;

import java.util.Arrays;
import java.util.HashSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Olawale
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig{
    
    
      @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(new HashSet<>(Arrays.asList("HTTP", "HTTPs")))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().title("My API").description("My API Documentation")
                        .version("1.0")
                .build())              
                ;
                }
}
