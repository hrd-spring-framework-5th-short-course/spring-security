package com.example.demospringsecurity.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceHandlerConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/swagger/**")
                .addResourceLocations("classpath:/static/swagger-v2.1.3/dist/");



    }

    @Override
    @Description("No need to create a logincontroller class")
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login-page").setViewName("login/login-page");

        registry.addViewController("/swagger-document").setViewName("swagger/index-v2.1.3");

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/api/**")
                .allowedMethods("*")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*");

    }
}
