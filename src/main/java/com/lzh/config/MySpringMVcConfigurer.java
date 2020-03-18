package com.lzh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MySpringMVcConfigurer  {
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){

        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("user/login");
                registry.addViewController("/index").setViewName("user/login");
                registry.addViewController("/register.html").setViewName("user/register");
                registry.addViewController("/index.html").setViewName("user/login");
                registry.addViewController("/forgetpwd").setViewName("user/forgot_password");

            }
        };
    }


    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }



    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                //指定要拦截的请求 /** 表示拦截所有请求
                .addPathPatterns("/**")
                //排除不需要拦截的请求路径
                .excludePathPatterns("/", "/index.html", "/register.html","/index")
                //springboot2+之后需要将静态资源文件的访问路径 也排除
                .excludePathPatterns("/css/*", "/img/*","/js/*");

    }

}
