package com.study.awswebstudy.config.auth;

import com.study.awswebstudy.domain.user.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


import java.util.stream.Stream;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity  // Spring Security() 설정들을 활성화 시켜줌.
public class SecurityConfiguration {
    private ClientRegistrationRepository clientRegistrationRepository;
    private final CustomOAuth2UserService customOAuth2UserService;

    private static final String[] PERMIT_ALL_PATTERNS = new String[] { //인가 허용 경로
            "/**",
            "/css/**",
            "/image/**",
            "/js/**",
            "/js/app/index.js",
            "/oauth2/**",

    };
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            HandlerMappingIntrospector handlerMappingIntrospector
    ) throws Exception {
        return httpSecurity
                .csrf().disable()
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/")  // 로그아웃시 경로로 이동?됨 로그아웃시 localhost:8080/?logout
                        //.userInfoEndpoint() //endpoint 검색 필요
                        //.userService(customOAuth2UserService)
                )

                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers(PathRequest.toH2Console())
                                .permitAll()
                                .requestMatchers(
                                        Stream
                                                .of(PERMIT_ALL_PATTERNS)
                                                .map(AntPathRequestMatcher::antMatcher)
                                                .toArray(AntPathRequestMatcher[]::new)
                                )
                                .permitAll()
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/**")).hasRole(UserRole.USER.name())
                                //.authenticated()
                )
        .build();
    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorizeRequests -> authorizeRequests
//                        .mvcMatchers("/", "/css/**", "/image/**", "/js/**", "/h2-console/**").permitAll()
//                        .mvcMatchers("/user").hasRole(UserRole.USER.name())
//                )
//                .oauth2Login(oauth2Login -> oauth2Login
//                        .loginPage("/login/oauth2")
//                )
//                .csrf(AbstractHttpConfigurer::disable);
//        return http.build();
//    }

//    @

//
//    private LogoutSuccessHandler oidcLogoutSuccessHandler() {
//        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
//                new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);
//
//        // Sets the location that the End-User's User Agent will be redirected to
//        // after the logout has been performed at the Provider
//        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}");
//
//        return oidcLogoutSuccessHandler;
//    }


}