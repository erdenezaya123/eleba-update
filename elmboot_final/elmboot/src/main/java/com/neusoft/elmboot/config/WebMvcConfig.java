package com.neusoft.elmboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域解决方案
 * origin:62.234.13.26:8082(后端腾讯云服务器)
 * 所有域名均可访问
 */

//@Configuration
public class WebMvcConfig {
    private static final String[] ALLOW_ORIGINS = {
            "http://localhost",
            "http://127.0.0.1",
            "http://62.234.13.26:8082"
    };
    private CorsConfiguration buildConfig() {
        //你配置了两个bean名一样的配置类
        //所以会报错
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许任何域名
        corsConfiguration.addAllowedOrigin("*");
        //允许任何头
        corsConfiguration.addAllowedHeader("*");
        //允许任何方法
        corsConfiguration.addAllowedMethod("*");
        //设置预检有效期
        corsConfiguration.setMaxAge(3600L);
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //注册
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}

