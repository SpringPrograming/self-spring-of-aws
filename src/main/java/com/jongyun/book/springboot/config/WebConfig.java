package com.jongyun.book.springboot.config;

import com.jongyun.book.springboot.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/*
* @LoginUser 어노테이션을 사용하기 위해 추가하는 작업
 */
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    /*
    * HandlerMethodArgumentResolver 는 항상
    * WebMvcConfigurer 의 addArgumentResolvers() 를 통해 추가해야 합니다.
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolver) {
        argumentResolver.add(loginUserArgumentResolver);
    }
}
