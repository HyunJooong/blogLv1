package com.sparta.myblog.config;

import com.sparta.myblog.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailService userDetailService;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean  // 스프링 시큐리티 기능 비활성화
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toMysqlConsole())
                .requestMatchers("/static/**");
    }

    // Mysql 경로 메서드
    private RequestMatcher toMysqlConsole() {
        return new AntPathRequestMatcher("/mysql-console");
    }

    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers("/login","/signup","/user").permitAll()
                        .anyRequest().authenticated())
                .formLogin((formLogin) -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/articles"))
                .logout((logOut) -> logOut
                        .logoutSuccessUrl("/login").invalidateHttpSession(true))
                .csrf((csrf) -> csrf
                        .disable())
                .build();
    }

    @Bean //인증 관리자 설정
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
            return authenticationConfiguration.getAuthenticationManager();
        }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
