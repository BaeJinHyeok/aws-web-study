package com.study.awswebstudy.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole { // Role 로 하니까 OAuthAttributes 클래스에서 role(Role.GUEST) 코드 입력시 다른 클래스와 충돌발생. Role -> UserRole 로 변경. User 클래스 Entity도 변경해줌

    GUEST("ROLE_GUEST", "손님"),           //private final String key; 로 GUEST(key,title) 적용 밑도 동일.
    USER("ROLE_USER", "일반 사용자");

    private final  String key;
    private final  String title;
}
