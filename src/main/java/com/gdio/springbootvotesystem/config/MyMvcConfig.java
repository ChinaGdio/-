package com.gdio.springbootvotesystem.config;

import com.gdio.springbootvotesystem.component.UserLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author gdio
 * @create 2020-02-17 17:59
 */
@Configuration
public class MyMvcConfig {
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        return new WebMvcConfigurerAdapter(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                //将atguigu请求映射到success页面
                registry.addViewController("signup.html").setViewName("signup");
                registry.addViewController("index.html").setViewName("index");
                registry.addViewController("/showDiagram.html").setViewName("showDiagram");
                registry.addViewController("/createVote.html").setViewName("createVote");
                registry.addViewController("/user.html").setViewName("user");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/tables.html").setViewName("tables");
                registry.addViewController("survey.html").setViewName("aboutUs");
                registry.addViewController("contact.html").setViewName("contactUs");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                String[] execlude={"/index.html",
                        "/","/login","/toRegister","/register","/register.html",
                        "/votelist","/signup.html","/contact.html","/survey.html",
                        "/contact","/login","/login.html","/managerLogin",
                        "/aboutUs.html","/toSuggest","/assets/*","/css/**","/img/**","/js/**"};
                registry.addInterceptor(new UserLoginInterceptor()).addPathPatterns("/**").excludePathPatterns(execlude);
            }
        };
    }
}
