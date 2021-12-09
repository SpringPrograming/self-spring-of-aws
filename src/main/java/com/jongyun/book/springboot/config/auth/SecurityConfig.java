package com.jongyun.book.springboot.config.auth;

import com.jongyun.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
// Spring Security 설정들을 활성화 시켜줍니다.
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // h2-console 화면을 사용하기 위해 해당 옵션들을 disable 합니다.
        http.csrf().disable().headers().frameOptions().disable()
                // authorizeRequests URL 별 권한 관리를 설정하는 옵션의 시작점 입니다.
                // authorizeRequests 가 선언 되어야만 antMatchers 옵션을 사용할 수 있습니다.
                .and().authorizeRequests()
                // antMatchers : 권한 관리 대상을 지정하는 옵션입니다.
                // URL, HTTP 메소드 별로 관리가 가능합니다.
                // "/" 등 지정된 URL 들은 permitALL() 옵션을 통해 전체 열람 권한을 주었습니다.
                // POST method 이면서 "/api/v1/**" 주소를 가진 API 는 USER 권한을 가진 사람만 가능 하도록 설계
                .antMatchers("/", "/css/**", "/images/**",
                        "/js/**", "h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // anyRequest: 설정된 값들 이외 나머지 URL 들을 나타냅니다.
                // 여기서는 authenticated 를 추가하여 나머지 URL 들은 모두 인증된 사용자들에게만
                // 허용하게 합니다.
                // 인증된 사용자 즉 로그인한 사용자들
                .anyRequest().authenticated()
                // 로그아웃 기능에 대한 여러 설정의 진입점 입니다.
                // 로그아웃 성공시 / 주소로 이동합니다.
                .and().logout().logoutSuccessUrl("/")
                .and().oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }
}
