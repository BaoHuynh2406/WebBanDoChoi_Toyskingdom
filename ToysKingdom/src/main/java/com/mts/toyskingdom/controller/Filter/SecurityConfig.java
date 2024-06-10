package com.mts.toyskingdom.controller.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public FilterRegistrationBean<Security> securityFilter() {
        FilterRegistrationBean<Security> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new Security());
        registrationBean.addUrlPatterns("/admin/"); // Áp dụng Security cho các đường dẫn /admin/*
        return registrationBean;
    }
}
