package com.study.awswebstudy.config.auth;

import com.study.awswebstudy.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security() 설정들을 활성화 시켜줌.
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/","/css/**", "/image/**", "/js/**", "h2-console/**").permitAll()
                        .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                        .formLogin(formLogin ->
                                formLogin
                                        .loginPage("/login")
                                        .permitAll()
                        )
                        .logout(logout ->
                                logout
                                        .logoutUrl("/logout")
                                        .logoutSuccessHandler(logoutSuccessHandler())
                                        .addLogoutHandler(logoutHandler())
                        )
                        .anyRequest().authenticated()
                        .anyRequest()
                )
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public LogoutHandler logoutHandler() {
        return new SecurityContextLogoutHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        SimpleUrlLogoutSuccessHandler successHandler = new SimpleUrlLogoutSuccessHandler();
        successHandler.setRedirectStrategy((request, response, url) -> {
            // 로그아웃 후 리다이렉트할 URL을 설정합니다.
            response.sendRedirect("/");
        });
        return successHandler;
    }

}