package app.configuration;

import app.interceptor.Authentication_Access;

import app.interceptor.Authentication_Refresh;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVC implements WebMvcConfigurer {

    private final Authentication_Access authentication_access_interceptor;
    private final Authentication_Refresh authentication_refresh_interceptor;

    public WebMVC(Authentication_Access authentication_access_interceptor, Authentication_Refresh authentication_refresh_interceptor) {
        this.authentication_access_interceptor = authentication_access_interceptor;
        this.authentication_refresh_interceptor = authentication_refresh_interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //registry.addInterceptor(authentication_access_interceptor).addPathPatterns("/**").excludePathPatterns("/user/put", "/user/id/check", "/user/login/check","/user/reissue");

        registry.addInterceptor(authentication_access_interceptor).addPathPatterns("/log/**");

        registry.addInterceptor(authentication_refresh_interceptor).addPathPatterns("/user/reissue");

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }
}
