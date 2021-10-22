package app.config;

import app.config.interceptor.Authentication;
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
        registry.addInterceptor(authentication)
                .addPathPatterns("/log/**","/game/get/info","/user/modify","/user/get/info", "/user/get/all", "/user/password/*","/user/reissue", "/stat/analysis/get","/admin/logout","/admin/get/main/info");

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("POST","OPTIONS")
                .allowedOrigins("http://localhost:8080","https://xradmin.super-brain.co.kr")
                .exposedHeaders("Authorization")
                .allowCredentials(true);
    }

}
