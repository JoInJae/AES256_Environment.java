package app.configuration;

import app.interceptor.Authentication_Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVC implements WebMvcConfigurer {

    private final Authentication_Interceptor authentication_interceptor;

    public WebMVC(Authentication_Interceptor authentication_interceptor) {
        this.authentication_interceptor = authentication_interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authentication_interceptor).addPathPatterns("/**").excludePathPatterns("/user/put", "/user/login/check", "/user/id/check", "/test/test");

    }
}
