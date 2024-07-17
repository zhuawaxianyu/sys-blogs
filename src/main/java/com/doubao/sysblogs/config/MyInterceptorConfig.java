package com.doubao.sysblogs.config;

import com.doubao.sysblogs.interceptor.JwtValidateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private JwtValidateInterceptor jwtValidateInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(jwtValidateInterceptor);
        registration
                .addPathPatterns("/api/**")
                .excludePathPatterns(
                        "/api/login",
                        "/api/auth/**",
                        "/api/auth/me",
                        "/api/auth/register",
                        "/api/posts",
                        "/api/posts(\\?.*|$)",
                        "/api/posts/\\d+$",
                        "/api/posts/\\d+");
    }
}
