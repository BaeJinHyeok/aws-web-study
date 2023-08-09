package com.study.awswebstudy.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security() 설정들을 활성화 시켜줌.
public class SecurityConfig extends WebSecurityConfigurer {
}
