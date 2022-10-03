package com.jkiller.killer.basics.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor());

        registry.addInterceptor(new OldLoginInterceptor())
                .addPathPatterns("/api/oldLogin");

        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/*")
                .excludePathPatterns("/admin/oldLogin");
    }
}
