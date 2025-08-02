package com.lease.web.admin.custom.config;

import com.lease.web.admin.custom.converter.StringToBaseEnumConverterFactory;
import com.lease.web.admin.custom.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final StringToBaseEnumConverterFactory stringToBaseEnumConverterFactory;
    private final AuthenticationInterceptor authenticationInterceptor;

    @Autowired
    public WebMvcConfiguration (StringToBaseEnumConverterFactory stringToBaseEnumConverterFactory, AuthenticationInterceptor authenticationInterceptor) {
        this.stringToBaseEnumConverterFactory = stringToBaseEnumConverterFactory;
        this.authenticationInterceptor = authenticationInterceptor;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(this.stringToBaseEnumConverterFactory);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authenticationInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login/**");
    }
}
