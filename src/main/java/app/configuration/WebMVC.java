package app.configuration;

import app.interceptor.Authentication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration public class WebMVC implements WebMvcConfigurer {

    private final Authentication authentication;

    public WebMVC(Authentication authentication) {

        this.authentication = authentication;

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authentication).addPathPatterns("/log/**","/user/modify","/user/get/info","/user/password/*","/user/reissue", "/stat/analysis/get","/admin/test");

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("POST","OPTIONS")
                .allowedOrigins("http://localhost:8080","https://xradmin.super-brain.co.kr")
                .allowCredentials(true);
    }

}
