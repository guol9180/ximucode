package com.ximu.init.config.mvc;

import com.ximu.init.auth.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: 析木
 * @description: 项目自定义配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private StringToBaseEnumConverterFactory stringToBaseEnumConverterFactory;

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override // 配置自定义类型转换器：String to enum
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(this.stringToBaseEnumConverterFactory);
    }

    @Override // 添加请求拦截器，获取token
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authInterceptor)
                .addPathPatterns("/api/**") // 配置拦截 URL
                .excludePathPatterns("/api/user/login", "/api/user/register", "/api/doc.html"); // 配置放行 URL
    }
}
