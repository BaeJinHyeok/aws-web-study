package com.study.awswebstudy.config.auth;

import com.study.awswebstudy.domain.user.UserRole;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.Customizer.withDefaults;

@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security() 설정들을 활성화 시켜줌.
@Configuration
public class SecurityConfiguration {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .httpBasic().disable()
//                .csrf().disable()
//                .formLogin().disable()
//                .logout().disable()
//                .securityContext((securityContext) -> {
//                    securityContext.securityContextRepository(delegatingSecurityContextRepository());
//                    securityContext.requireExplicitSave(true);
//                })
//                .authorizeHttpRequests()
//                .requestMatchers(request -> AUTH_WHITELIST.contains(request.getRequestURI())).permitAll()
//                .anyRequest().authenticated();
//
//        return httpSecurity.build();
//    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http
//                .csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .requestMatchers("")
//                .permitAll()
//                .requestMatchers("/", "/css/**", "/image/**", "/js/**", "/h2-console/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvide)
//
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // ...
                .csrf().disable()
                .securityContext((securityContext) -> securityContext
                        .requireExplicitSave(true)

                );
        return http.build();
    }
//    @Bean
//    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http
//                // ...
//                .csrf().disable()
//        //...
//        return http.build();
//    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                //.csrf().disable().cors().disable()
//                .authorizeHttpRequests(request -> request
//                        //.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//                        //.requestMatchers("/", "/css/**", "/image/**", "/js/**", "/h2-console/**").permitAll()
//                        .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
//                )
//                .formLogin(login -> login	// form 방식 로그인 사용
//                        .defaultSuccessUrl("/", true)	// 성공 시 dashboard로
//                        .permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
//                )
//                .logout(withDefaults());	// 로그아웃은 기본설정으로 (/logout으로 인증해제)
//
//        return http.build();
//    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
//        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector).servletPath("/path");
//        http
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll()
//                        .requestMatchers(mvcMatcherBuilder.pattern("/user")).hasRole(UserRole.USER.name())
//                );
//        return http.build();
//        //git test
//        //git test
//        //git test
//    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authorize) -> authorize
//                                .requestMatchers("/", "/css/**", "/image/**", "/js/**", "/h2-console/**").permitAll()
//                                .requestMatchers("/api/v1/**").hasRole(UserRole.USER.name())
//                                .anyRequest().authenticated()
//                )
//                .formLogin(formLogin ->
//                        formLogin
//                                .loginPage("/login")
//                                .permitAll()
//                )
//                .logout(logout ->
//                        logout
//                                .logoutUrl("/logout")
//                                .logoutSuccessHandler(logoutSuccessHandler())
//                                .addLogoutHandler(logoutHandler())
//                )
//                .httpBasic(withDefaults());
//        return http.build();
//    }



//    @Bean
//    public LogoutHandler logoutHandler() {
//        return new SecurityContextLogoutHandler();
//    }
//
//    @Bean
//    public LogoutSuccessHandler logoutSuccessHandler() {
//        SimpleUrlLogoutSuccessHandler successHandler = new SimpleUrlLogoutSuccessHandler();
//        successHandler.setRedirectStrategy((request, response, url) -> {
//            // 로그아웃 후 리다이렉트할 URL을 설정합니다.
//            response.sendRedirect("/");
//        });
//        return successHandler;
//    }

}
