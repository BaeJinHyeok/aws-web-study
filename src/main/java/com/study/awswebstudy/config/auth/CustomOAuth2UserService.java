package com.study.awswebstudy.config.auth;

import com.study.awswebstudy.config.auth.dto.OAuthAttributes;
import com.study.awswebstudy.config.auth.dto.SessionUser;
import com.study.awswebstudy.domain.user.Users;
import com.study.awswebstudy.domain.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2user = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,
                oAuth2user.getAttributes());

        Users user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));
        // 세션에 사용자 정보를 저장하기 위한 DTO 클래스. User 클래스를 쓰지 않고 새로만들어 쓰는 이유는 나중에..
        //  이유 -> Failed to convert from type 에러 발생
        // 이는 세션에 저장하기위해 User 클래스를 세션에 저장하려고하니 User클래스에 직렬화를 구현하지않았다는 의미의 에러임
        // User는 클래스가 엔티티임 -> 언제 다른 엔티티와 관계가 형성될지 모름
        // ex) @OneToMany, @ManyToMany 등 자식 엔티티를 갖고있다면 직렬화 대상에 자식도 포함됨 -> 성능이슈, 부수효과 발생 확률 상승
        // 그래서 직렬화 기능을 가진 세션 Dto를 하나 추가로 만드는 것이 운영 및 유지보수

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());

    }

    private Users saveOrUpdate(OAuthAttributes attributes) {
        Users user = userRepository.findByEmail(attributes.getEmail()).map(entity -> entity.update(attributes.getName(), attributes.getPicture())).orElse(attributes.toEntity());

        return userRepository.save(user);
    }

}

