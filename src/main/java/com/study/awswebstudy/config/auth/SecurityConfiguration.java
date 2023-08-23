package com.study.awswebstudy.config.auth;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.server.csrf.CsrfToken;



import static org.hibernate.query.sqm.tree.SqmNode.log;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity  // Spring Security() 설정들을 활성화 시켜줌.
public class SecurityConfiguration {
    private ClientRegistrationRepository clientRegistrationRepository;
    private final CustomOAuth2UserService customOAuth2UserService;

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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                //.httpBasic().disable()
                .csrf(c -> {
                    c.csrfTokenRepository(customTokenRepository()); //csrf 람다식으로 변경? 스프링6.x 부트 3.x
                })
                //.csrf().disable()
                .oauth2Login(oauth2 -> oauth2  //Oauth2 login 구현
                        .loginPage("/login/oauth2"))

                //oauth2 logout code...



                //.authorizeHttpRequests(authorize -> authorize
                //        .requestMatchers(new AntPathRequestMatcher("/", "css/**", "/image/**", "/js/**", "/h2-console/**")).permitAll())
//                .securityContext((securityContext) -> securityContext
//                        .requireExplicitSave(true)
                .securityContext((securityContext) -> securityContext
                        .securityContextRepository(new RequestAttributeSecurityContextRepository())

                );

        return http.build();
    }

    public class CustomCsrfTokenRepository implements CsrfTokenRepository {
        @Override
        public org.springframework.security.web.csrf.CsrfToken generateToken(HttpServletRequest request){
            log.info(1);
            {

                @Override
                public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response){
                log.info(2);
            }

                @Override
                public CsrfToken loadToken(HttpServletRequest request){
                log.info(3);
            }
            }
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