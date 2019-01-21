package com.example.demospringsecurity.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
public class SwaggerApiDocumentConfig {

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()

                .apis(RequestHandlerSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("com.example.demospringsecurity"))
                .paths(PathSelectors.any())
//                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(apiKey()));
    }


    private ApiInfo apiInfo() {

        List<VendorExtension> vendorExtensions = new ArrayList<>();

        Contact contact = new Contact("Yong Yong", "www.yongyong.com", "yongyong@gmail.com");

        return new ApiInfo(
                "KSHRD Short Course - 6th",
                "Description (KSHRD Short Course - 6th)",
                "Version 1.0",
                "TermsOfServiceUrl",
                contact,
                "license",
                "licenseUrl",
                vendorExtensions);
    }


    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration(
                null,
                null,
                null,
                null,
                null,
                null,
                true);
    }


}
