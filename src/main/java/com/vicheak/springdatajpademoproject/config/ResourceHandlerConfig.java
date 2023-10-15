package com.vicheak.springdatajpademoproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceHandlerConfig implements WebMvcConfigurer {

    @Value("${resources.server-path}")
    private String resServerPath;
    @Value("${resources.client-path}")
    private String resClientPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resClientPath)
                .addResourceLocations("classpath:" + resServerPath);
    }
}
